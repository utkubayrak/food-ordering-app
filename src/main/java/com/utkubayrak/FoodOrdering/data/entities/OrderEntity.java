package com.utkubayrak.FoodOrdering.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity { //bir siparişi temsil eder

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //bir siparişi veren bir müşteri olabilir ancak bir müşteri birden fazla sipariş verebilir.
    private UserEntity customer;

    @JsonIgnore
    @ManyToOne //bir sipariş bir restorana ait olabilir ancak bir restoran birden fazla sipariş alabilir.
    private RestaurantEntity restaurant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne //bir sipariş bir adrese ait olabilir ancak bir adreste birden fazla sipariş olabilir.
    private AddressEntity deliveryAddress;

    @OneToMany //bir sipariş birden fazla sipariş öğesine sahip olabilir.
    private List<OrderItemEntity> items;

    // private Payment payment;

    private int totalItem;

    private int totalPrice;
}
