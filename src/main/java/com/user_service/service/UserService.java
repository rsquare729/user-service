package com.user_service.service;

import java.util.List;

import com.user_service.dto.UserRequestDto;
import com.user_service.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getAllUsers();
}