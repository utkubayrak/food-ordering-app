package com.utkubayrak.FoodOrdering.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class IngredientsItemEntity { //bir malzeme öğesini temsil eder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne //bir malzeme öğesi bir kategoriye ait olabilir ancak bir kategoriye birden fazla malzeme öğesi ait olabilir.
    private IngredientCategoryEntity category; //Malzeme öğesinin kategori bilgisi

    @JsonIgnore
    @ManyToOne // bir malzeme öğesi bir restorana ait olabilir ancak bir restorana birden fazla malzeme öğesi ait olabilir.
    private RestaurantEntity restaurant;

    private boolean inStoke = true;
}
