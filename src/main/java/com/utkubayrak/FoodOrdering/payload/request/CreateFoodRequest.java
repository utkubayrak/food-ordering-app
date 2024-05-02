package com.utkubayrak.FoodOrdering.payload.request;

import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.IngredientsItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private CategoryEntity category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasional;
    private List<IngredientsItemEntity> ingredients;
}
