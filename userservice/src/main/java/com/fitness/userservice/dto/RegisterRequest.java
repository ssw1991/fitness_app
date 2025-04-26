package com.fitness.userservice.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Bad email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message="Password must be 6 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message= " Last name is required")
    private String lastName;

}
