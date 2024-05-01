package com.utkubayrak.FoodOrdering.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utkubayrak.FoodOrdering.business.dto.RestaurantDto;
import com.utkubayrak.FoodOrdering.data.USER_ROLE;
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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    //bir kullanıcı birden fazla siparişe sahip olabilir.  eğer bir kullanıcı silindiğinde, o kullanıcıya ait tüm siparişlerin de silineceğini belirtir.OrderEntity sınıfında customer adında bir alanın olması beklenir ve bu alanın hangi alanla eşleştiği mappedBy ifadesiyle belirtilir.
    private List<OrderEntity> orders = new ArrayList<>();
    // Kullanıcının verdiği siparişleri temsil eden liste. @JsonIgnore annotation'ı, JSON dönüşümünde bu alanın dışarıya açık olmadığını belirtir.
    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList();
    // Kullanıcının favori restoranlarını temsil eden liste. @ElementCollection annotation'ı, RestaurantDto nesnelerinin koleksiyonunu temsil ettiğini belirtir.bu koleksiyon, kullanıcıya ait favori restoranlar gibi ayrı bir tabloda değil, aynı UserEntity tablosu içinde saklanır.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //bir kullanıcının birden fazla adresi olabilir. @OneToMany annotation'ı bu ilişkiyi belirtir.
    // eğer bir kullanıcıya ait adreslerin listesinden bir adres çıkarılırsa, bu adresin veritabanından da silinmesi gerektiğini belirtir.
    private List<AddressEntity> addresses = new ArrayList<>();

}
