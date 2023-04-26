package com.nutranet.Model.Repository;

import com.nutranet.Model.Entity.FoodDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FoodDataRepository extends JpaRepository<FoodDataEntity, Integer> {

    @Query(nativeQuery = true, value="select * from food_data order by gu_prdlst_mnf_manage_no desc limit 10 ;")
    Optional<FoodDataEntity> find10Items();
}
