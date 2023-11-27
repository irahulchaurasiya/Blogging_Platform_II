package com.example.blogging.models.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInInput {

    @Email(message = "Enter a Valid Email")
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
