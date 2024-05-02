package com.utkubayrak.FoodOrdering.controller;


import com.utkubayrak.FoodOrdering.business.services.RestaurantService;
import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.payload.request.CreateRestaurantRequest;
import com.utkubayrak.FoodOrdering.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<RestaurantEntity> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        RestaurantEntity restaurantEntity = restaurantService.createRestaurant(req, userEntity);
        return new ResponseEntity<>(restaurantEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantEntity> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        RestaurantEntity restaurantEntity = restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurantEntity, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        restaurantService.deletedRestaurant(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("restaurant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<RestaurantEntity> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        RestaurantEntity restaurant = restaurantService.updateRestaurantStatus(jwt,id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<RestaurantEntity> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserEntity userEntity = userService.findUserByJwtToken(jwt);

        RestaurantEntity restaurant = restaurantService.getRestaurantByUserId(userEntity.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
