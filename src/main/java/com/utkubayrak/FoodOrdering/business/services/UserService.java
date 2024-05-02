package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.data.entities.UserEntity;

public interface UserService {

    public UserEntity findUserByJwtToken(String jwt) throws Exception;

    public UserEntity findUserByEmail(String email) throws Exception;
}
