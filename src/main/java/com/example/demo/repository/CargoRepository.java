package com.example.demo.repository;

import com.example.demo.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Long> {
    @Query("from CargoEntity order by createAt desc")
    List<CargoEntity> findAllOrderByDesc();



}