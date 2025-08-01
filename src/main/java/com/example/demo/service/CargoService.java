package com.example.demo.service;

import com.example.demo.dto.CargoRequestDTO;
import com.example.demo.dto.CargoResponseDTO;
import com.example.demo.entity.CargoEntity;
import com.example.demo.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;


    public List<CargoResponseDTO> findCargoOrderByCreatedAt() {
        List<CargoEntity> allOrderByDesc =
                cargoRepository.findAllOrderByDesc();

        List<CargoResponseDTO> responseList = new ArrayList<>();
        for (CargoEntity cargoEntity : allOrderByDesc) {
            CargoResponseDTO responseDTO = new CargoResponseDTO();
            responseDTO.setId(cargoEntity.getId());
            responseDTO.setFromARegion(cargoEntity.getFromARegion());
            responseDTO.setFromBDistrict(cargoEntity.getFromBDistrict());
            responseDTO.setToARegion(cargoEntity.getToARegion());
            responseDTO.setToBDistrict(cargoEntity.getToBDistrict());
            responseDTO.setCreateAt(cargoEntity.getCreateAt());
            responseDTO.setTransportType(cargoEntity.getTransportType());
            responseDTO.setUpdatedAt(cargoEntity.getUpdatedAt());

            responseList.add(responseDTO);
        }

        return responseList;
    }

    public void create(CargoRequestDTO cargoRequestDTO) {
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setFromARegion(cargoRequestDTO.getFromARegion());
        cargoEntity.setFromBDistrict(cargoRequestDTO.getFromBDistrict());

        cargoEntity.setToARegion(cargoRequestDTO.getToARegion());
        cargoEntity.setToBDistrict(cargoRequestDTO.getToBDistrict());

        cargoEntity.setTransportType(cargoRequestDTO.getTransportType());

        cargoEntity.setCreateAt(LocalDateTime.now());
        cargoEntity.setUpdatedAt(LocalDateTime.now());
        cargoRepository.save(cargoEntity);
    }


}
