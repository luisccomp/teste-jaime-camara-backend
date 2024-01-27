package br.com.jaime.camara.testejaimecamarabackend.domain.repository.specs;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaJuridica;

public class PessoaJuridicaSpecs {
    public static Specification<PessoaJuridica> fromDataCadastro(LocalDate dataCadastro) {
        if (dataCadastro == null)
            return null;

        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("dataCadastro"), dataCadastro);
        };
    }

    public static Specification<PessoaJuridica> byRazaoSocial(String razaoSocial) {
        if (razaoSocial == null)
            return null;

        return (root, query, builder) -> {
            return builder.like(builder.upper(root.get("razaoSocial")), razaoSocial);
        };
    }

    public static Specification<PessoaJuridica> byCnpj(String cnpj) {
        if (cnpj == null)
            return null;

        return (root, query, builder) -> {
            return builder.equal(root.get("cnpj"), cnpj);
        };
    }
}
