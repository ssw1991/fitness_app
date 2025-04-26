package com.fitness.userservice.dto;
import com.fitness.userservice.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    public UserResponse(User user){
        /*
        The inclusion of this constructor, removes all automatically generated constructors
        delivered by the @Data annotation.  This may at somepoint require the inclusion of
        a required args constructor if it becomes  neccessary.
         */
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        createdAt = user.getCreatedAt();
        updatedAt = user.getCreatedAt();
    }
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
