package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CargoResponseDTO {
    private Long id;

    private String fromARegion;
    private String fromBDistrict;

    private String toARegion;
    private String toBDistrict;


    private String transportType;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    private List<String> cargoPhotoList;
}
