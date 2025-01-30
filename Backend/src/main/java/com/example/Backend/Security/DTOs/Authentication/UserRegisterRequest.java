package com.example.Backend.Security.DTOs.Authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    public @NotBlank @Size(min = 3, max = 50) String getUsername() {
        return username;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public @NotBlank @Size(min = 6) String getPassword() {
        return password;
    }
}
