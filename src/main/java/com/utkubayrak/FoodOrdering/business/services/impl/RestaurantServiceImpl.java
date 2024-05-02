package com.utkubayrak.FoodOrdering.business.services.impl;

import com.utkubayrak.FoodOrdering.business.dto.RestaurantDto;
import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.data.entities.AddressEntity;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.data.repository.AddressRepository;
import com.utkubayrak.FoodOrdering.data.repository.RestaurantRepository;
import com.utkubayrak.FoodOrdering.data.repository.UserRepository;
import com.utkubayrak.FoodOrdering.payload.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImp userService;

    @Override
    public RestaurantEntity createRestaurant(CreateRestaurantRequest req, UserEntity userEntity) {
        AddressEntity address = addressRepository.save(req.getAddress());

        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(userEntity);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public RestaurantEntity updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        RestaurantEntity restaurant = findRestaurantById(restaurantId);
        if (restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (restaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deletedRestaurant(Long restaurantId) throws Exception {

        RestaurantEntity restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<RestaurantEntity> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<RestaurantEntity> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public RestaurantEntity findRestaurantById(Long id) throws Exception {
        Optional<RestaurantEntity> opt = restaurantRepository.findById(id);

        if (opt.isEmpty()) {
            throw new Exception("Restaurant not found with id" + id);
        }
        return opt.get();
    }

    @Override
    public RestaurantEntity getRestaurantByUserId(Long userId) throws Exception {
        RestaurantEntity restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found with owner id " + userId);
        }
        
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, UserEntity userEntity) throws Exception {

        RestaurantEntity restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurantId);

        boolean isFavorited = false;
        List<RestaurantDto> favorites = userEntity.getFavorites();
        for (RestaurantDto favorite : favorites){
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }
        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        }else {
            favorites.add(restaurantDto);
        }
        userRepository.save(userEntity);
        return restaurantDto;
    }

    @Override
    public RestaurantEntity updateRestaurantStatus(String jwt, Long id) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        RestaurantEntity restaurant = findRestaurantById(id);
        if (!restaurant.getOwner().getId().equals(userEntity.getId())) {
            throw new Exception("Bu işlemi yapmaya yetkiniz yok.");
        }
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
