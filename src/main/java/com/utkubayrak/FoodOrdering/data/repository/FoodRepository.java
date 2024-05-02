package com.utkubayrak.FoodOrdering.data.repository;

import com.utkubayrak.FoodOrdering.data.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

    List<FoodEntity> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM FoodEntity f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    List<FoodEntity> searchFood(@Param("keyword") String keyword);
}
