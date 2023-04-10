package com.nutranet.Model.Repository;

import com.nutranet.Model.Entity.FoodDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDataRepository extends JpaRepository<FoodDataEntity, Integer> {
}
