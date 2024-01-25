package br.com.jaime.camara.testejaimecamarabackend.service.impl;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaJuridica;
import br.com.jaime.camara.testejaimecamarabackend.domain.repository.PessoaJuridicaRepository;
import br.com.jaime.camara.testejaimecamarabackend.domain.repository.specs.PessoaJuridicaSpecs;
import br.com.jaime.camara.testejaimecamarabackend.exception.BadRequestException;
import br.com.jaime.camara.testejaimecamarabackend.exception.BusinessException;
import br.com.jaime.camara.testejaimecamarabackend.exception.NotFoundException;
import br.com.jaime.camara.testejaimecamarabackend.exception.messages.ClienteErrorMessages;
import br.com.jaime.camara.testejaimecamarabackend.exception.messages.PessoaJuridicaErrorMessages;
import br.com.jaime.camara.testejaimecamarabackend.service.PessoaJuridicaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Override
    public Page<PessoaJuridica> listarClientesPessoaJuridica(Pageable pageable, Map<String, Object> queryParams) {
        var specs = Specification
                .where(PessoaJuridicaSpecs.fromDataCadastro((LocalDate) queryParams.get("dataCadastro")))
                .and(PessoaJuridicaSpecs.byRazaoSocial((String) queryParams.get("razaoSocial")))
                .and(PessoaJuridicaSpecs.byCnpj((String) queryParams.get("cnpj")));

        return this.pessoaJuridicaRepository.findAll(specs, pageable);
    }

    @Override
    public PessoaJuridica cadastrarClientePessoaJuridica(PessoaJuridica pessoaJuridica) throws BusinessException {
        if (this.pessoaJuridicaRepository.existsByCnpj(pessoaJuridica.getCnpj()))
            throw new BadRequestException(PessoaJuridicaErrorMessages.CNPJ_ALREADY_REGISTERED);

        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }

    @Override
    public PessoaJuridica buscarClientePessoaJuridicaPorId(Long id) throws BusinessException {
        return this.pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ClienteErrorMessages.CLIENTE_NOT_FOUND));
    }

    @Override
    public PessoaJuridica atualizarClientePessoaJuridica(PessoaJuridica pessoaJuridica) throws BusinessException {
        if (!this.pessoaJuridicaRepository.existsById(pessoaJuridica.getId()))
            throw new NotFoundException(ClienteErrorMessages.CLIENTE_NOT_FOUND);
        
        else if (this.pessoaJuridicaRepository.existsByCnpjAndNotId(pessoaJuridica.getCnpj(), pessoaJuridica.getId()))
            throw new BadRequestException(PessoaJuridicaErrorMessages.CNPJ_ALREADY_REGISTERED);

        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }

    @Override
    public void excluirClientePessoaJuridica(Long id) throws BusinessException {
        var pessoaJuridica = this.pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PessoaJuridicaErrorMessages.CNPJ_ALREADY_REGISTERED));

        this.pessoaJuridicaRepository.delete(pessoaJuridica);
    }
}
