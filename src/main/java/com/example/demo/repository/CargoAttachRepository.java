package com.example.demo.repository;


import com.example.demo.entity.CargoAttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoAttachRepository extends JpaRepository<CargoAttachEntity, Long> {
    List<CargoAttachEntity> findByCargoId(Long cargoId);
}