package com.utkubayrak.FoodOrdering.business.services.impl;

import com.utkubayrak.FoodOrdering.business.services.CategoryService;
import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryEntity createCategory(String name, Long userId) throws Exception {
        RestaurantEntity restaurantEntity = restaurantService.getRestaurantByUserId(userId);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setRestaurant(restaurantEntity);

        return categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> findCategoryByRestaurantId(Long id) throws Exception {
        RestaurantEntity restaurantEntity = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurantEntity.getId());
    }

    @Override
    public CategoryEntity findCategoryById(Long id) throws Exception {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);
        if (optionalCategoryEntity.isEmpty()) {
            throw new Exception("category not found");
        }
        return optionalCategoryEntity.get();
    }
}
