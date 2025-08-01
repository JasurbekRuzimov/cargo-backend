package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// DTO - Data Transfer Object
@Data
public class CargoRequestDTO {

    @NotBlank(message = "From A Region must not be blank")
    @Size(min = 1, max = 50, message = "From A Region must be between 1 and 50 characters")
    private String fromARegion;

    @NotBlank(message = "From B District must not be blank")
    @Size(min = 1, max = 50, message = "From B District must be between 1 and 50 characters")
    private String fromBDistrict;

    @NotBlank(message = "To A Region must not be blank")
    @Size(min = 1, max = 50, message = "To A Region must be between 1 and 50 characters")
    private String toARegion;

    @NotBlank(message = "To B District must not be blank")
    @Size(min = 1, max = 50, message = "To B District must be between 1 and 50 characters")
    private String toBDistrict;

    @NotBlank(message = "Transport Type must not be blank")
    @Size(min = 1, max = 30, message = "Transport Type must be between 1 and 30 characters")
    private String transportType;
}