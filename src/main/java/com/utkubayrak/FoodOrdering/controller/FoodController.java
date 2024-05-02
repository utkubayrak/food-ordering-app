package com.utkubayrak.FoodOrdering.controller;

import com.utkubayrak.FoodOrdering.business.services.FoodService;
import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.FoodEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<FoodEntity>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        List<FoodEntity> foodEntities = foodService.searchFood(name);

        return new ResponseEntity<>(foodEntities, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodEntity>> getRestaurantFood(@RequestParam boolean vegetarian,@RequestParam boolean seasonal,@RequestParam boolean nonVeg,@RequestParam(required = false) String foot_category, @PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        List<FoodEntity> foodEntities = foodService.getRestaurantsFood(restaurantId,vegetarian,nonVeg,seasonal,foot_category);

        return new ResponseEntity<>(foodEntities, HttpStatus.OK);
    }
}
