package com.utkubayrak.FoodOrdering.data.repository;

import com.utkubayrak.FoodOrdering.data.entities.CartEntity;
import com.utkubayrak.FoodOrdering.data.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

}
