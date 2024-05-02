package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.business.dto.RestaurantDto;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.payload.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public RestaurantEntity createRestaurant(CreateRestaurantRequest req, UserEntity userEntity);

    public RestaurantEntity updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deletedRestaurant(Long restaurantId) throws Exception;

    public List<RestaurantEntity> getAllRestaurant();

    public List<RestaurantEntity> searchRestaurant(String keyword);

    public RestaurantEntity findRestaurantById(Long id) throws Exception;

    public RestaurantEntity getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, UserEntity userEntity) throws Exception;

    public RestaurantEntity updateRestaurantStatus(String jwt, Long id) throws Exception;
}
