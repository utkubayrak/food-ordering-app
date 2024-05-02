package com.utkubayrak.FoodOrdering.data.repository;

import com.utkubayrak.FoodOrdering.data.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query("SELECT r FROM RestaurantEntity r WHERE lower(r.name) LIKE lower(concat('%',:query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
    List<RestaurantEntity> findBySearchQuery(String query);
    RestaurantEntity findByOwnerId(Long userId);
}
