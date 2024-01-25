package br.com.jaime.camara.testejaimecamarabackend.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaJuridica;
import br.com.jaime.camara.testejaimecamarabackend.exception.BusinessException;

public interface PessoaJuridicaService {
    Page<PessoaJuridica> listarClientesPessoaJuridica(Pageable pageable, Map<String, Object> queryParams);

    PessoaJuridica cadastrarClientePessoaJuridica(PessoaJuridica pessoaJuridica) throws BusinessException;

    PessoaJuridica buscarClientePessoaJuridicaPorId(Long id) throws BusinessException;

    PessoaJuridica atualizarClientePessoaJuridica(PessoaJuridica pessoaJuridica) throws BusinessException;

    void excluirClientePessoaJuridica(Long id) throws BusinessException;
}
