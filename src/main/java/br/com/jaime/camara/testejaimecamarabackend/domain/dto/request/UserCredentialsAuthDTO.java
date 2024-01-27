package br.com.jaime.camara.testejaimecamarabackend.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCredentialsAuthDTO {
    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String senha;
}
