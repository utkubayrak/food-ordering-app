package com.utkubayrak.FoodOrdering.data.repository;

import com.utkubayrak.FoodOrdering.data.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    public List<CategoryEntity> findByRestaurantId(Long id);
}
