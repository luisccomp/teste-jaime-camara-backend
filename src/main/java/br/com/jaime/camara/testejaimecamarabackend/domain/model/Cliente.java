package br.com.jaime.camara.testejaimecamarabackend.domain.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 12)
    private String telefone;

    @Column(name = "data_cadastro")
    @CreationTimestamp
    private LocalDate dataCadastro;

    @Column(name = "data_alteracao")
    @UpdateTimestamp
    private LocalDate dataAlteracao;
}
