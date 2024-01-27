package br.com.jaime.camara.testejaimecamarabackend.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoas_juridicas")
@Getter
@Setter
public class PessoaJuridica extends Cliente {
    @Column(unique = true, length = 14)
    private String cnpj;

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;
}
