package com.utkubayrak.FoodOrdering.controller;

import com.utkubayrak.FoodOrdering.business.dto.RestaurantDto;
import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<RestaurantEntity>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        List<RestaurantEntity> restaurantEntity = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurantEntity, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        List<RestaurantEntity> restaurantEntity = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurantEntity, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantEntity> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        RestaurantEntity restaurantEntity = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurantEntity, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        RestaurantDto restaurantDto = restaurantService.addToFavorites(id, userEntity);

        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }


}
