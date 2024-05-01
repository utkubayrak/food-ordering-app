package com.utkubayrak.FoodOrdering.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class FoodEntity { //bir yiyeceği temsil eder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne // bir yiyecek bir kategoriye ait olabilir ancak bir kategoriye birden fazla yiyecek ait olabilir.
    private CategoryEntity foodCategory;

    @Column(length = 1000)
    @ElementCollection //bu alanın bir değer koleksiyonu olduğunu belirtir.
    private List<String> images;

    private boolean available; //Yiyecek mevcut mu ?

    @ManyToOne //bir yiyecek bir restorana ait olabilir ancak bir restorana birden fazla yiyecek ait olabilir.
    private RestaurantEntity restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;


    @ManyToMany //bir yiyecek birçok malzeme içerebilir ve aynı malzeme birçok yiyeceğe ait olabilir.
    private List<IngredientsItemEntity> ingredients = new ArrayList<>();

    private Date creationDate;
}
