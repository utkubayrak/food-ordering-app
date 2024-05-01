package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.payload.request.CreateRestaurantRequest;

public interface IRestaurantService {

    public RestaurantEntity createRestaurant(CreateRestaurantRequest req, UserEntity userEntity);
}
