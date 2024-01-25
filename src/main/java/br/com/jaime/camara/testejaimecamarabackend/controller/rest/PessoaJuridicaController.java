package br.com.jaime.camara.testejaimecamarabackend.controller.rest;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jaime.camara.testejaimecamarabackend.domain.dto.request.DadosClientePJRequestDTO;
import br.com.jaime.camara.testejaimecamarabackend.domain.dto.response.DadosClientePJResponseDTO;
import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaJuridica;
import br.com.jaime.camara.testejaimecamarabackend.exception.BusinessException;
import br.com.jaime.camara.testejaimecamarabackend.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pessoas-juridicas")
@RequiredArgsConstructor
public class PessoaJuridicaController {
    private final PessoaJuridicaService pessoaJuridicaService;

    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<DadosClientePJResponseDTO> cadastrarClientePessoaJuridica(
        @RequestBody @Valid DadosClientePJRequestDTO dadosCliente
    ) throws BusinessException {
        var pessoaJuridica = this.pessoaJuridicaService.cadastrarClientePessoaJuridica(mapper.map(dadosCliente, PessoaJuridica.class));

        return ResponseEntity.created(URI.create("/api/pessoas-juridicas/" + pessoaJuridica.getId()))
                .body(this.mapper.map(pessoaJuridica, DadosClientePJResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<Page<DadosClientePJResponseDTO>> listarClientes(
        Pageable pageable,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataCadastro,
        @RequestParam(required = false) String razaoSocial,
        @RequestParam(required = false) String cnpj
    ) {
        var queryParams = new HashMap<String, Object>();
        queryParams.put("dataCadastro", dataCadastro);
        queryParams.put("razaoSocial", razaoSocial);
        queryParams.put("cnpj", cnpj);

        var pessoasFisicas = this.pessoaJuridicaService.listarClientesPessoaJuridica(pageable, queryParams)
                .map(pessoaJuridica -> this.mapper.map(pessoaJuridica, DadosClientePJResponseDTO.class));

        return ResponseEntity.ok(pessoasFisicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosClientePJResponseDTO> buscarClientePessoaJuridica(@PathVariable Long id) throws BusinessException {
        var pessoaJuridica = this.pessoaJuridicaService.buscarClientePessoaJuridicaPorId(id);

        return ResponseEntity.ok(this.mapper.map(pessoaJuridica, DadosClientePJResponseDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosClientePJResponseDTO> atualizarDadosClientePessoaJuridica(@PathVariable Long id, @RequestBody @Valid DadosClientePJRequestDTO dadosCliente) throws BusinessException {
        var pessoaJuridica = this.pessoaJuridicaService.atualizarClientePessoaJuridica(this.mapper.map(dadosCliente, PessoaJuridica.class));

        return ResponseEntity.ok(this.mapper.map(pessoaJuridica, DadosClientePJResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirClientePessoaJuridica(@PathVariable Long id) throws BusinessException {
        this.pessoaJuridicaService.excluirClientePessoaJuridica(id);

        return ResponseEntity.noContent().build();
    }
}
