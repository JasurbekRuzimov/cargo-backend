package com.example.demo.service;

import com.example.demo.entity.UserAttachEntity;
import com.example.demo.repository.UserAttachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAttachService {
    private final UserAttachRepository userAttachRepository;

    public List<UserAttachEntity> getUserAttachByUserId(Long userId){
        return userAttachRepository.findAllByUserId(userId);
    }
}