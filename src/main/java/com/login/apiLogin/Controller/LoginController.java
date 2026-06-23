package com.login.apiLogin.Controller;

import com.login.apiLogin.DTOs.LoginRequestDTO;
import com.login.apiLogin.DTOs.LoginResponseDTO;
import com.login.apiLogin.Service.JwtService;
import com.login.apiLogin.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> autenticarUsuario(
            @Valid @RequestBody LoginRequestDTO loginRequest) {

        userService.createUser();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        String token = jwtService.generateToken(loginRequest.username());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
