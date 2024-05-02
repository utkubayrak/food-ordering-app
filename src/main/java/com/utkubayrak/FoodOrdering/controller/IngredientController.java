package com.utkubayrak.FoodOrdering.controller;

import com.utkubayrak.FoodOrdering.business.services.IngredientsService;
import com.utkubayrak.FoodOrdering.data.entities.IngredientCategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.IngredientsItemEntity;
import com.utkubayrak.FoodOrdering.payload.request.IngredientCategoryRequest;
import com.utkubayrak.FoodOrdering.payload.request.IngredientItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategoryEntity> createIngredientCategory(@RequestBody IngredientCategoryRequest req) throws Exception {
        IngredientCategoryEntity ingredientCategoryEntity = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());

        return new ResponseEntity<>(ingredientCategoryEntity, HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<IngredientsItemEntity> createIngredientItem(@RequestBody IngredientItemRequest req) throws Exception {
        IngredientsItemEntity ingredientsItemEntity = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());

        return new ResponseEntity<>(ingredientsItemEntity, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItemEntity> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientsItemEntity ingredientsItemEntity = ingredientsService.updateStock(id);

        return new ResponseEntity<>(ingredientsItemEntity, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItemEntity>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientsItemEntity> ingredientsItemEntities = ingredientsService.findRestaurantsIngredients(id);

        return new ResponseEntity<>(ingredientsItemEntities, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategoryEntity>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategoryEntity> ingredientCategoryEntities = ingredientsService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(ingredientCategoryEntities, HttpStatus.OK);
    }
}
