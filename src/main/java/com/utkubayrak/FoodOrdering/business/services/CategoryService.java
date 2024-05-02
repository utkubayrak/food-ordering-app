package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public CategoryEntity createCategory(String name, Long userId) throws Exception;

    public List<CategoryEntity> findCategoryByRestaurantId(Long id) throws Exception;

    public CategoryEntity findCategoryById(Long id) throws Exception;
}
