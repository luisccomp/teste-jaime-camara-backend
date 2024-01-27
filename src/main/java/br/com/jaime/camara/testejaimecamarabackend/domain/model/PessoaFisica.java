package br.com.jaime.camara.testejaimecamarabackend.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoas_fisicas")
@Getter
@Setter
@NoArgsConstructor
public class PessoaFisica extends Cliente {
    @Column
    private LocalDate dataNascimento;

    @Column(length = 11, unique = true)
    private String cpf;

    @Column
    private String nome;
}
