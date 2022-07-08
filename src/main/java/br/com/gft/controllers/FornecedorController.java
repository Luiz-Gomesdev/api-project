package br.com.gft.controllers;

import br.com.gft.dto.fornecedor.ConsultaFornecedorDTO;
import br.com.gft.dto.fornecedor.FornecedorMapper;
import br.com.gft.dto.fornecedor.RegistroFornecedorDTO;
import br.com.gft.entities.Fornecedor;
import br.com.gft.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<Page<ConsultaFornecedorDTO>> buscarTodosFornecedores(@PageableDefault Pageable pageable) {

        return ResponseEntity.ok(fornecedorService.listarTodosFornecedores(pageable).map(FornecedorMapper::fromEntity));
    }

    @PostMapping
    public ResponseEntity<ConsultaFornecedorDTO> salvarFornecedor(@RequestBody RegistroFornecedorDTO dto) {

        Fornecedor fornecedor = fornecedorService.salvarFornecedor(FornecedorMapper.fromDTO(dto));

        return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaFornecedorDTO> buscarFornecedor(@PathVariable Long id) {

        Fornecedor fornecedor = fornecedorService.buscarFornecedor(id);

        return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaFornecedorDTO> alterarFornecedor(@RequestBody RegistroFornecedorDTO dto,
                                                           @PathVariable Long id) {

        try {
            Fornecedor fornecedor = fornecedorService.atualizarFornecedor(FornecedorMapper.fromDTO(dto), id);

            return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaFornecedorDTO> excluirFornecedor(@PathVariable Long id) {

        try {
            fornecedorService.excluirFornecedor(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
