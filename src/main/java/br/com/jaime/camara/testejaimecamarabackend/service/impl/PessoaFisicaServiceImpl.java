package br.com.jaime.camara.testejaimecamarabackend.service.impl;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaFisica;
import br.com.jaime.camara.testejaimecamarabackend.domain.repository.PessoaFisicaRepository;
import br.com.jaime.camara.testejaimecamarabackend.domain.repository.specs.PessoaFisicaSpecs;
import br.com.jaime.camara.testejaimecamarabackend.exception.BadRequestException;
import br.com.jaime.camara.testejaimecamarabackend.exception.BusinessException;
import br.com.jaime.camara.testejaimecamarabackend.exception.NotFoundException;
import br.com.jaime.camara.testejaimecamarabackend.exception.messages.ClienteErrorMessages;
import br.com.jaime.camara.testejaimecamarabackend.exception.messages.PessoaFisicaErrorMessages;
import br.com.jaime.camara.testejaimecamarabackend.service.PessoaFisicaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaFisicaServiceImpl implements PessoaFisicaService {
    private final PessoaFisicaRepository pessoaFisicaRepository;
    
    @Override
    public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws BusinessException {
        if (this.pessoaFisicaRepository.existsByCpf(pessoaFisica.getCpf())) {
            throw new BadRequestException(PessoaFisicaErrorMessages.CPF_ALREADY_REGISTERED);
        }

        return this.pessoaFisicaRepository.save(pessoaFisica);
    }

    @Override
    public Page<PessoaFisica> listarClientesPessoaFisica(Pageable pageable, Map<String, Object> queryParams) {
        var specs = Specification
                .where(PessoaFisicaSpecs.fromDataCadasto((LocalDate) queryParams.get("dataCadastro")))
                .and(PessoaFisicaSpecs.byNome((String) queryParams.get("nome")))
                .and(PessoaFisicaSpecs.byCpf((String) queryParams.get("cpf")));

        return this.pessoaFisicaRepository.findAll(specs, pageable);
    }
    
    @Override
    public PessoaFisica buscarPessoaFisicaPorId(Long id) throws BusinessException {
        return this.pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ClienteErrorMessages.CLIENTE_NOT_FOUND));
    }

    @Override
    public void excluirClientePessoaFisica(Long id) throws BusinessException {
        var pessoaFisica = this.pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ClienteErrorMessages.CLIENTE_NOT_FOUND));

        this.pessoaFisicaRepository.delete(pessoaFisica);
    }

    @Override
    public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica) throws BusinessException {
        if (!this.pessoaFisicaRepository.existsById(pessoaFisica.getId()))
            throw new NotFoundException(ClienteErrorMessages.CLIENTE_NOT_FOUND);

        else if (this.pessoaFisicaRepository.existsByCpfAndNotId(pessoaFisica.getCpf(), pessoaFisica.getId()))
            throw new BadRequestException(PessoaFisicaErrorMessages.CPF_ALREADY_REGISTERED);

        return this.pessoaFisicaRepository.save(pessoaFisica);
    }
}