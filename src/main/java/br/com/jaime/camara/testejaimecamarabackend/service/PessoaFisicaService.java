package br.com.jaime.camara.testejaimecamarabackend.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaFisica;
import br.com.jaime.camara.testejaimecamarabackend.exception.BusinessException;

public interface PessoaFisicaService {
    PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws BusinessException;

    Page<PessoaFisica> listarClientesPessoaFisica(Pageable pageable, Map<String, Object> queryParams);

    PessoaFisica buscarPessoaFisicaPorId(Long id) throws BusinessException;

    void excluirClientePessoaFisica(Long id) throws BusinessException;

    PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica) throws BusinessException;
}
