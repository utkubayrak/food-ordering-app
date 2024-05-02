package com.utkubayrak.FoodOrdering.controller;

import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        UserEntity userEntity = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}
