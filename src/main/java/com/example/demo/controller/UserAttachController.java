package com.example.demo.controller;

import com.example.demo.entity.UserAttachEntity;
import com.example.demo.service.UserAttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user/attach")
@RequiredArgsConstructor
public class UserAttachController {
    private final UserAttachService userAttachService;


    @GetMapping("/get/{userId}")
    public ResponseEntity<List<UserAttachEntity>> getUserAttach(@PathVariable Long userId) {
        return ResponseEntity.ok(userAttachService.getUserAttachByUserId(userId));
    }
}