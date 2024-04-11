package com.pblgllgs.backend.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @Email(message = "Invalid email")
        @NotEmpty(message = "Email can not be empty")
        @NotBlank(message = "Email can not be empty")
        String email,
        @NotEmpty(message = "Password can not be empty")
        @NotBlank(message = "Password can not be empty")
        @Size(min = 8, max = 20, message = "Password must have between 8 and 20 characters")
        String password
) {
}
