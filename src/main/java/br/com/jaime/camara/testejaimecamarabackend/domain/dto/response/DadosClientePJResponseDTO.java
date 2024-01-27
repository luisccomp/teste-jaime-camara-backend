package br.com.jaime.camara.testejaimecamarabackend.domain.dto.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DadosClientePJResponseDTO {
    private Long id;

    private String telefone;

    private String cnpj;

    private String razaoSocial;

    private String nomeFantasia;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAlteracao;
}
