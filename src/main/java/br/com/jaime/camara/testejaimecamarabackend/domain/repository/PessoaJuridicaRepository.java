package br.com.jaime.camara.testejaimecamarabackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>, JpaSpecificationExecutor<PessoaJuridica> {
    boolean existsByCnpj(String cnpj);

    @Query("SELECT CASE WHEN (count(pj) > 0) THEN true ELSE false END " +
           "FROM PessoaJuridica pj " +
           "WHERE pj.id <> :id " +
           "AND pj.cnpj = :cnpj")
    boolean existsByCnpjAndNotId(String cnpj, Long id);
}
