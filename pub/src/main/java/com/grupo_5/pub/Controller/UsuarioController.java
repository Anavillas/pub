package com.grupo_5.pub.Controller;

import com.grupo_5.pub.Infra.Security.TokenService;
import com.grupo_5.pub.DTO.LoginRequestDTO;
import com.grupo_5.pub.DTO.LoginResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                data.getUsername(),
                data.getPassword()
        );

        authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken(data.getUsername());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}

