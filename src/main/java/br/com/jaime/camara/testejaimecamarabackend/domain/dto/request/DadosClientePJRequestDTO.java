package br.com.jaime.camara.testejaimecamarabackend.domain.dto.request;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DadosClientePJRequestDTO {
    @NotBlank
    private String telefone;

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotBlank
    private String razaoSocial;

    @NotBlank
    private String nomeFantasia;
}
