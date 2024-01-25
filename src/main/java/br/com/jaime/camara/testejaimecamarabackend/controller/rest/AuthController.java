package br.com.jaime.camara.testejaimecamarabackend.controller.rest;

import br.com.jaime.camara.testejaimecamarabackend.domain.dto.request.UserCredentialsAuthDTO;
import br.com.jaime.camara.testejaimecamarabackend.domain.dto.response.AuthResponseDTO;
import br.com.jaime.camara.testejaimecamarabackend.domain.model.Usuario;
import br.com.jaime.camara.testejaimecamarabackend.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid UserCredentialsAuthDTO credentials) {
        var userCredentials = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getSenha());
        var auth = authenticationManager.authenticate(userCredentials);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        var response = new AuthResponseDTO();
        response.setAccessToken(token);

        return ResponseEntity.ok(response);
    }
}
