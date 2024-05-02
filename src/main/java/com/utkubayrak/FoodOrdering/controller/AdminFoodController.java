package com.utkubayrak.FoodOrdering.controller;


import com.utkubayrak.FoodOrdering.business.services.FoodService;
import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.FoodEntity;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.payload.request.CreateFoodRequest;
import com.utkubayrak.FoodOrdering.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<FoodEntity> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        RestaurantEntity restaurantEntity = restaurantService.findRestaurantById(req.getRestaurantId());
        FoodEntity foodEntity = foodService.createFood(req, req.getCategory(), restaurantEntity);

        return new ResponseEntity<>(foodEntity, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("food deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FoodEntity> updateFoodAvaibilityStatus(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        FoodEntity foodEntity = foodService.updateAvailibilityStatus(id);
        return new ResponseEntity<>(foodEntity, HttpStatus.CREATED);
    }

}
