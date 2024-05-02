package com.utkubayrak.FoodOrdering.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class CartEntity { //bir alışveriş sepetini temsil eder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity customer;

    private Long total;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) //bir öğe yalnızca bir alışveriş sepetine ait olabilir.Alışveriş sepetinden kaldırılan öğelerin otomatik olarak veritabanından silinmesini sağlar.
    private List<CartItemEntity> items = new ArrayList<>(); //Alışveriş sepetinde bulunan öğelerin listesi
}
