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

import br.com.jaime.camara.testejaimecamarabackend.domain.dto.request.DadosClientePFRequestDTO;
import br.com.jaime.camara.testejaimecamarabackend.domain.dto.response.DadosClientePFResponseDTO;
import br.com.jaime.camara.testejaimecamarabackend.domain.model.PessoaFisica;
import br.com.jaime.camara.testejaimecamarabackend.exception.BusinessException;
import br.com.jaime.camara.testejaimecamarabackend.service.PessoaFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pessoas-fisicas")
@RequiredArgsConstructor
public class PessoaFisicaController {
    private final PessoaFisicaService pessoaFisicaService;

    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<DadosClientePFResponseDTO> cadastrarPessoaFisica(
        @RequestBody @Valid DadosClientePFRequestDTO dadosCliente
    ) throws BusinessException {
        var pessoaFisica = pessoaFisicaService.cadastrarPessoaFisica(this.mapper.map(dadosCliente, PessoaFisica.class));

        return ResponseEntity.created(URI.create("/api/pessoas-fisicas/" + pessoaFisica.getId()))
                .body(this.mapper.map(pessoaFisica, DadosClientePFResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<Page<DadosClientePFResponseDTO>> listarClientes(
        Pageable pageable, 
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataCadastro, 
        @RequestParam(required = false) String nome, 
        @RequestParam(required = false) String cpf
    ) {
        var queryParams = new HashMap<String, Object>();
        queryParams.put("dataCadastro", dataCadastro);
        queryParams.put("nome", nome);
        queryParams.put("cpf", cpf);

        var clientes = this.pessoaFisicaService.listarClientesPessoaFisica(pageable, queryParams)
                .map(pessoaFisica -> this.mapper.map(pessoaFisica, DadosClientePFResponseDTO.class));

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosClientePFResponseDTO> buscarClientePessoaFisicaPorID(@PathVariable Long id) throws BusinessException {
        var pessoaFisica = this.pessoaFisicaService.buscarPessoaFisicaPorId(id);

        return ResponseEntity.ok(this.mapper.map(pessoaFisica, DadosClientePFResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirClientePessoaFisica(@PathVariable Long id) throws BusinessException {
        this.pessoaFisicaService.excluirClientePessoaFisica(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosClientePFResponseDTO> atualizarDadosClientePessoaFisica(
        @PathVariable Long id, 
        @RequestBody @Valid DadosClientePFRequestDTO dadosCliente
    ) throws BusinessException {
        var pessoaFisica = this.mapper.map(dadosCliente, PessoaFisica.class);
        pessoaFisica.setId(id);

        pessoaFisica = this.pessoaFisicaService.atualizarPessoaFisica(pessoaFisica);

        return ResponseEntity.ok(this.mapper.map(pessoaFisica, DadosClientePFResponseDTO.class));
    }
}
