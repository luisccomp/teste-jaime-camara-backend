package br.com.jaime.camara.testejaimecamarabackend.domain.dto.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DadosClientePFRequestDTO {
    @NotBlank
    private String telefone;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String nome;
}
