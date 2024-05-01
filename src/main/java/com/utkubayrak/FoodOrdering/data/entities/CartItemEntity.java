package com.utkubayrak.FoodOrdering.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class CartItemEntity { //bir alışveriş sepetindeki bir öğeyi temsil eder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne //bir alışveriş sepeti öğesi bir alışveriş sepetine ait olabilir ancak bir alışveriş sepetine birden fazla öğe ait olabilir.
    private CartEntity cart;

    @ManyToOne //bir alışveriş sepeti öğesi bir yiyeceğe ait olabilir ancak bir yiyeceğe birden fazla alışveriş sepeti öğesi ait olabilir.
    private FoodEntity food;

    private int quantity;

    private List<String> ingredients;

    private Long totalPrice;
}
