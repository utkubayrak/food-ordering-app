package com.utkubayrak.FoodOrdering.controller;

import com.utkubayrak.FoodOrdering.business.services.CategoryService;
import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity categoryEntity, @RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        CategoryEntity createdCategory = categoryService.createCategory(categoryEntity.getName(), userEntity.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    @GetMapping("/category/restaurant")
    public ResponseEntity<List<CategoryEntity>> getRestaurantCategory(@RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        List<CategoryEntity> categories = categoryService.findCategoryByRestaurantId(userEntity.getId());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
