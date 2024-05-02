package com.utkubayrak.FoodOrdering.business.services.impl;

import com.utkubayrak.FoodOrdering.business.services.IngredientsService;
import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.data.entities.IngredientCategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.IngredientsItemEntity;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.repository.IngredientCategoryRepository;
import com.utkubayrak.FoodOrdering.data.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategoryEntity createIngredientCategory(String name, Long restaurantId) throws Exception {

        RestaurantEntity restaurantEntity = restaurantService.findRestaurantById(restaurantId);

        IngredientCategoryEntity ingredientCategoryEntity = new IngredientCategoryEntity();
        ingredientCategoryEntity.setRestaurant(restaurantEntity);
        ingredientCategoryEntity.setName(name);
        return ingredientCategoryRepository.save(ingredientCategoryEntity);
    }

    @Override
    public IngredientCategoryEntity findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategoryEntity> optionalIngredientCategoryEntity = ingredientCategoryRepository.findById(id);

        if (optionalIngredientCategoryEntity.isEmpty()) {
            throw new Exception("ingredient category not found...");
        }
        return optionalIngredientCategoryEntity.get();
    }

    @Override
    public List<IngredientCategoryEntity> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItemEntity createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        RestaurantEntity restaurantEntity = restaurantService.findRestaurantById(restaurantId);
        IngredientCategoryEntity ingredientCategoryEntity = findIngredientCategoryById(categoryId);
        IngredientsItemEntity ingredientsItemEntity = new IngredientsItemEntity();
        ingredientsItemEntity.setName(ingredientName);
        ingredientsItemEntity.setRestaurant(restaurantEntity);
        ingredientsItemEntity.setCategory(ingredientCategoryEntity);

        IngredientsItemEntity ingredientsItem = ingredientItemRepository.save(ingredientsItemEntity);
        ingredientCategoryEntity.getIngredients().add(ingredientsItem);

        return ingredientsItem;
    }

    @Override
    public List<IngredientsItemEntity> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItemEntity updateStock(Long id) throws Exception {
        Optional<IngredientsItemEntity> optionalIngredientsItemEntity = ingredientItemRepository.findById(id);
        if (optionalIngredientsItemEntity.isEmpty()) {
            throw new Exception("ingredient not found");
        }
        IngredientsItemEntity ingredientsItemEntity = optionalIngredientsItemEntity.get();
        ingredientsItemEntity.setInStoke(!ingredientsItemEntity.isInStoke());
        return ingredientItemRepository.save(ingredientsItemEntity);
    }
}
