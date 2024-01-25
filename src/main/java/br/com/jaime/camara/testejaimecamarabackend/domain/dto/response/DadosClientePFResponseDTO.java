package br.com.jaime.camara.testejaimecamarabackend.domain.dto.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DadosClientePFResponseDTO {
    private Long id;

    private String telefone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private String cpf;

    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAlteracao;
}
