package com.utkubayrak.FoodOrdering.data.repository;

import com.utkubayrak.FoodOrdering.data.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
}
