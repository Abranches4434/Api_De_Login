package com.login.apiLogin.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

        @NotNull(message = "Username deve ser preenchido")
        @Email(message = "Deve ser um email válido!")
        @Size(max = 100, message = "Username deve conter no máximo 100 caracteres")
        String username,

        @NotNull(message = "Password deve ser preenchido")
        @Size(min = 6, max = 100, message = "Password deve conter entre 6 e 100 caracteres")
        String password

) {}
