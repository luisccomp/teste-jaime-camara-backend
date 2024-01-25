package br.com.jaime.camara.testejaimecamarabackend.domain.repository.specs;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaFisica;

public class PessoaFisicaSpecs {
    public static Specification<PessoaFisica> fromDataCadasto(LocalDate dataCadastro) {
        if (dataCadastro == null)
            return null;

        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("dataCadastro"), dataCadastro);
        };
    }

    public static Specification<PessoaFisica> byNome(String nome) {
        if (nome == null)
            return null;

        return (root, query, builder) -> {
            return builder.equal(root.get("nome"), nome);
        };
    }

    public static Specification<PessoaFisica> byCpf(String cpf) {
        if (cpf == null)
            return null;

        return (root, query, builder) -> {
            return builder.equal(root.get("cpf"), cpf);
        };
    }

    private PessoaFisicaSpecs() {

    }
}
