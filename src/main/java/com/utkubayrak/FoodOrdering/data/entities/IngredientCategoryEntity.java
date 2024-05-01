package com.utkubayrak.FoodOrdering.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class IngredientCategoryEntity { //malzeme kategorisini temsil eder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne //bir malzeme kategorisi bir restorana ait olabilir ancak bir restorana birden fazla malzeme kategorisi ait olabilir.
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //bir malzeme kategorisinin birçok malzemesi olabilir ancak bir malzeme yalnızca bir kategoriye ait olabilir.
    private List<IngredientsItemEntity> ingredients = new ArrayList<>(); //Bu kategoriye ait malzemelerin listesi
}
