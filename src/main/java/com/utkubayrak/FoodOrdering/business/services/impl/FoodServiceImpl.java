package com.utkubayrak.FoodOrdering.business.services.impl;

import com.utkubayrak.FoodOrdering.business.services.FoodService;
import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.FoodEntity;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.repository.FoodRepository;
import com.utkubayrak.FoodOrdering.payload.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public FoodEntity createFood(CreateFoodRequest req, CategoryEntity categoryEntity, RestaurantEntity restaurantEntity) {

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodCategory(categoryEntity);
        foodEntity.setRestaurant(restaurantEntity);
        foodEntity.setDescription(req.getDescription());
        foodEntity.setImages(req.getImages());
        foodEntity.setName(req.getName());
        foodEntity.setPrice(req.getPrice());
        foodEntity.setIngredients(req.getIngredients());
        foodEntity.setSeasonal(req.isSeasional());
        foodEntity.setVegetarian(req.isVegetarian());

        FoodEntity savedFood = foodRepository.save(foodEntity);
        restaurantEntity.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        FoodEntity foodEntity = findFoodById(foodId);
        foodEntity.setRestaurant(null);
        foodRepository.save(foodEntity);
    }

    @Override
    public List<FoodEntity> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {

        List<FoodEntity> foodEntities = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian){
            foodEntities = filterByVegetarian(foodEntities, isVegetarian);
        }
        if (isNonVeg){
            foodEntities = filterByNonVeg(foodEntities, isNonVeg);
        }
        if (isSeasonal){
            foodEntities = filterBySeasonal(foodEntities, isSeasonal);
        }
        if (foodCategory != null && !foodCategory.equals("")){
            foodEntities = filterByCategory(foodEntities, foodCategory);
        }

        return null;
    }

    private List<FoodEntity> filterByCategory(List<FoodEntity> foodEntities, String foodCategory) {
        return foodEntities.stream().filter(food -> {
            if (food.getFoodCategory() != null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<FoodEntity> filterBySeasonal(List<FoodEntity> foodEntities, boolean isSeasonal) {
        return foodEntities.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<FoodEntity> filterByNonVeg(List<FoodEntity> foodEntities, boolean isNonVeg) {
        return foodEntities.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());
    }

    private List<FoodEntity> filterByVegetarian(List<FoodEntity> foodEntities, boolean isVegetarian) {

        return foodEntities.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<FoodEntity> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public FoodEntity findFoodById(Long foodId) throws Exception {
        Optional<FoodEntity> optionalFood = foodRepository.findById(foodId);

        if (optionalFood.isEmpty()){
            throw new Exception("food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public FoodEntity updateAvailibilityStatus(Long foodId) throws Exception {
        FoodEntity foodEntity = findFoodById(foodId);
        foodEntity.setAvailable(!foodEntity.isAvailable());

        return foodRepository.save(foodEntity);
    }
}
