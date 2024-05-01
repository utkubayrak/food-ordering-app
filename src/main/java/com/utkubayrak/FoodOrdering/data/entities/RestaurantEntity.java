package com.utkubayrak.FoodOrdering.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RestaurantEntity { //bir restoranı temsil eder

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity owner; //her restoranın bir kullanıcıya bağlı olduğunu gösterir.

    private String name;

    private String description;

    private String cuisineType; //Restoranın mutfağını temsil eden alan.

    @OneToOne
    private AddressEntity address; //her restoranın bir adresle ilişkilendirildiğini gösterir.

    @Embedded //ContactInformationEntity sınıfı, RestaurantEntity sınıfının bir parçası gibi davranır
    private ContactInformationEntity contactInformation;

    private String openingHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    // bir restoranın birden fazla siparişi olabileceğini belirtir.
    private List<OrderEntity> orders = new ArrayList<>();

    @ElementCollection //ilişkisel bir tablo yerine, ana tablonun içinde bir koleksiyon olarak depolanacak verileri belirtir.
    @Column(length = 1000)
    private List<String> images;

    private LocalDateTime registrationDate;

    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    //bir restoranın birden fazla yiyeceği olabileceğini belirtir.
    private List<FoodEntity> foods = new ArrayList<>();

}
