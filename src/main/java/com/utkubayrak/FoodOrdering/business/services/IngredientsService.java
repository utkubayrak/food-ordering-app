package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.data.entities.IngredientCategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.IngredientsItemEntity;

import java.util.List;

public interface IngredientsService {

    public IngredientCategoryEntity createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategoryEntity findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategoryEntity> findIngredientCategoryByRestaurantId(Long id) throws Exception;
    public IngredientsItemEntity createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;
    public List<IngredientsItemEntity> findRestaurantsIngredients(Long restaurantId);


    public IngredientsItemEntity updateStock(Long id) throws Exception;


}
