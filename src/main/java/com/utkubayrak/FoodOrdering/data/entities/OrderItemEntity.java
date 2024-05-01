package com.utkubayrak.FoodOrdering.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItemEntity { //bir sipariş öğesini temsil eder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //bir sipariş öğesinin bir yiyeceğe ait olabileceğini ancak bir yiyeceğin birden fazla sipariş öğesine ait olabileceği
    private FoodEntity food;

    private int quantity; //Sipariş öğesinde kaç adet yiyecek olduğunu temsil eden alan.

    private Long totalPrice;

    private List<String> ingredients; //Sipariş öğesinde kullanılan malzemeler
}
