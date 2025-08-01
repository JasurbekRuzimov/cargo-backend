package com.example.demo.controller;

import com.example.demo.dto.AttachResponseDTO;
import com.example.demo.service.AttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/attach")
@RequiredArgsConstructor
public class AttachController {
  private final AttachService service;

  @PostMapping("/upload")
  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
    AttachResponseDTO fileName = service.saveToSystem(file);
    return ResponseEntity.ok().body(fileName);
  }

  @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] open(@PathVariable("fileName") String fileName) {
    return service.open(fileName);
  }

  @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
  public byte[] open_general(@PathVariable("fileName") String fileName) {
    return service.open_general(fileName);
  }

  @GetMapping("/download/{fineName}")
  public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
    Resource file = service.download(fileName);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
      "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
