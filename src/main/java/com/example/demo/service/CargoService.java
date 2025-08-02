package com.example.demo.service;

import com.example.demo.dto.CargoRequestDTO;
import com.example.demo.dto.CargoResponseDTO;
import com.example.demo.entity.CargoAttachEntity;
import com.example.demo.entity.CargoEntity;
import com.example.demo.repository.CargoAttachRepository;
import com.example.demo.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;
    private final CargoAttachRepository cargoAttachRepository;

//    public Page<CargoResponseDTO> findCargoOrderByCreatedAt(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//
//
//        Page<CargoEntity> allOrderByDesc = cargoRepository.findAllOrderByDesc(pageable);
//        Page<CargoResponseDTO> response = allOrderByDesc.map(entity -> {
//            CargoResponseDTO cargoResponseDTO = new CargoResponseDTO();
//            cargoResponseDTO.setId(entity.getId());
//            cargoResponseDTO.setTransportType(entity.getTransportType());
//            cargoResponseDTO.setCreateAt(entity.getCreateAt());
//            cargoResponseDTO.setUpdatedAt(entity.getUpdatedAt());
//
//            cargoResponseDTO.setFromARegion(entity.getFromARegion());
//            cargoResponseDTO.setFromBDistrict(entity.getFromBDistrict());
//
//            cargoResponseDTO.setToARegion(entity.getToBDistrict());
//            cargoResponseDTO.setToBDistrict(entity.getToBDistrict());
//
//            return cargoResponseDTO;
//        });
//
//
//        return response;
//    }



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


    public Page<CargoResponseDTO> findCargoOrderByCreatedAt(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CargoEntity> allOrderByDesc = cargoRepository.findAllOrderByDesc(pageable);
        Page<CargoResponseDTO> response = allOrderByDesc.map(entity -> {
            CargoResponseDTO dto = new CargoResponseDTO();
            dto.setId(entity.getId());
            dto.setFromARegion(entity.getFromARegion());
            dto.setFromBDistrict(entity.getFromBDistrict());
            dto.setToARegion(entity.getToARegion());
            dto.setToBDistrict(entity.getToBDistrict());
            dto.setTransportType(entity.getTransportType());
            dto.setCreateAt(entity.getCreateAt());
            dto.setUpdatedAt(entity.getUpdatedAt());

            List<CargoAttachEntity> byCargoId = cargoAttachRepository.findByCargoId(entity.getId());
            List<String> photoUrls = new ArrayList<>();
            for (CargoAttachEntity cargoAttachEntity : byCargoId) {
                photoUrls.add("http://localhost:8080/attach/open/" + cargoAttachEntity.getAttach().getId());
            }
            dto.setCargoPhotoList(photoUrls);

            return dto;
        });

        return response;
    }

    @Transactional
    public void deleteCargoById(Long id) {
        cargoRepository.updateCargoActiveFalseById(id);
        throw new RuntimeException("JHHHHH");
    }


    public void attachLink(Long cargoId, String attachId) {
        CargoAttachEntity cargoAttachEntity = new CargoAttachEntity();
        cargoAttachEntity.setCargoId(cargoId);
        cargoAttachEntity.setAttachId(attachId);
        cargoAttachRepository.save(cargoAttachEntity);
    }
}
