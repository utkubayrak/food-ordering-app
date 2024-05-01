package com.utkubayrak.FoodOrdering.payload.request;


import com.utkubayrak.FoodOrdering.data.entities.AddressEntity;
import com.utkubayrak.FoodOrdering.data.entities.ContactInformationEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private AddressEntity address;
    private ContactInformationEntity contactInformation;
    private String openingHours;
    private List<String> images;

}
