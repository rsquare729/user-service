package com.user_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user_service.dto.UserRequestDto;
import com.user_service.dto.UserResponseDto;
import com.user_service.entity.User;
import com.user_service.mapper.UserMapper;
import com.user_service.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log =
            LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	
	UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        log.info("Creating user with email: {}", dto.getEmail());
        User user = UserMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        log.info("User created with ID: {}", savedUser.getId());
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        log.info("Fetching user with ID: {}", id);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		log.info("User found: {}", user.getEmail());
		return UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        log.info("Total users found: {}", users.size());
        return users.stream().map(UserMapper::toDto).toList();
    }
}
