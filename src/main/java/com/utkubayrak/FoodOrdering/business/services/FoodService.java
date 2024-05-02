package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.FoodEntity;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.payload.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public FoodEntity createFood(CreateFoodRequest req, CategoryEntity categoryEntity, RestaurantEntity restaurantEntity);

    void deleteFood(Long foodId) throws Exception;

    public List<FoodEntity> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory);

    public List<FoodEntity> searchFood(String keyword);

    public FoodEntity findFoodById(Long foodId) throws Exception;

    public FoodEntity updateAvailibilityStatus(Long foodId) throws Exception;
}
