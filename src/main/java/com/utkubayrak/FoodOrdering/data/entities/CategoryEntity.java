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
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne //bir kategori bir restorana ait olabilir ancak bir restorana birden fazla kategori ait olabilir.
    private RestaurantEntity restaurant; //Kategorinin hangi restorana ait olduğunu temsil eden alan.
}
