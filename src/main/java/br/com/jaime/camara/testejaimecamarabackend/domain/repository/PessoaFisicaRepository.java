package br.com.jaime.camara.testejaimecamarabackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>, JpaSpecificationExecutor<PessoaFisica> {
    boolean existsByCpf(String cpf);

    @Query("SELECT CASE WHEN (count(pf) > 0) THEN true ELSE false END " +
           "FROM PessoaFisica pf " +
           "WHERE pf.id <> :id " +
           "AND pf.cpf = :cpf")
    boolean existsByCpfAndNotId(@Param("cpf") String cpf, @Param("id") Long id);
}
