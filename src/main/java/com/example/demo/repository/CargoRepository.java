package com.example.demo.repository;

import com.example.demo.entity.CargoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<CargoEntity, Long> {

    @Query("from CargoEntity where active = true order by createAt desc")
    Page<CargoEntity> findAllOrderByDesc(Pageable pageable);

    @Modifying
    @Query("update CargoEntity SET active = false where id =?1 ")
    void updateCargoActiveFalseById(Long id);




}