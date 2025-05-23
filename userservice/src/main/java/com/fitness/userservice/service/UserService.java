package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.respository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repo;

    private UserResponse genUserResponse(User user){
        UserResponse response = new UserResponse(user);
        return response;
    }

    public UserResponse getUserProfile(String userId) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return genUserResponse(user);
    }

    public UserResponse register(@Valid RegisterRequest request) {

        if(repo.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        User saved_user = repo.save(user);
        return genUserResponse(saved_user);
    }

    public Boolean existByUserId(String userId) {
        log.info("Calling User Validation API for userId: {}", userId);
        return repo.existsById(userId);
    }
}
