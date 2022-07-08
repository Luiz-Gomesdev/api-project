package br.com.gft.controllers;

import br.com.gft.dto.produto.ConsultaProdutoDTO;
import br.com.gft.dto.produto.ProdutoMapper;
import br.com.gft.dto.produto.RegistroProdutoDTO;
import br.com.gft.entities.Produto;
import br.com.gft.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/produto")
public class produtoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ConsultaProdutoDTO>> buscarTodosOsProdutos(@PageableDefault Pageable pageable) {

        return ResponseEntity.ok(produtoService.listarTodosProdutos(pageable).map(ProdutoMapper::fromEntity));
    }

    @PostMapping
    public ResponseEntity<ConsultaProdutoDTO> salvarProduto(@RequestBody RegistroProdutoDTO dto) {

        Produto produto = produtoService.salvarProduto(ProdutoMapper.fromDTO(dto));

        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
    }
}
