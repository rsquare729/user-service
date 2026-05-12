package com.user_service.mapper;

import com.user_service.dto.UserRequestDto;
import com.user_service.dto.UserResponseDto;
import com.user_service.entity.User;

public class UserMapper {
	public static User toEntity(UserRequestDto dto) {
				return new User(null, dto.getName(), dto.getEmail(), dto.getRole());
	}

	public static UserResponseDto toDto(User user) {
		return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole());	
	}
}
