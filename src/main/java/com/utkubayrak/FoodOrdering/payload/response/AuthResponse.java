package com.utkubayrak.FoodOrdering.payload.response;

import com.utkubayrak.FoodOrdering.data.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;

}
