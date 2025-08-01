package com.example.demo.controller;

import com.example.demo.dto.CargoRequestDTO;
import com.example.demo.dto.CargoResponseDTO;
import com.example.demo.service.CargoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cargo")
@RequiredArgsConstructor
@CrossOrigin
public class CargoController {
    private final CargoService cargoService;

    @PostMapping("/create")
    public ResponseEntity<Void> createCargo(
            @Valid @RequestBody CargoRequestDTO cargoRequestDTO
    ) {
        cargoService.create(cargoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CargoResponseDTO>> findAllCargo() {
        return ResponseEntity.ok(
                cargoService.findCargoOrderByCreatedAt()
        );
    }
}
