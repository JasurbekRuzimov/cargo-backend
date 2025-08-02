package com.example.demo.controller;

import com.example.demo.dto.CargoRequestDTO;
import com.example.demo.dto.CargoResponseDTO;
import com.example.demo.service.CargoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PostMapping("/attach/link")
    public ResponseEntity<Void> attachLink(
            @RequestParam Long cargoId,
            @RequestParam String attachId
    ) {
        cargoService.attachLink(cargoId, attachId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Page<CargoResponseDTO>> findAllCargo(
            @RequestParam int page, @RequestParam int size
    ) {
        return ResponseEntity.ok(
                cargoService.findCargoOrderByCreatedAt(page, size)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargoById(id);
        return ResponseEntity.ok().build();
    }



}
