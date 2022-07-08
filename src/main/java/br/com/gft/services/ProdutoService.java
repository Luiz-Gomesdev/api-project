package br.com.gft.services;

import br.com.gft.entities.Produto;
import br.com.gft.exception.EntityNotFoundException;
import br.com.gft.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {

        return produtoRepository.save(produto);
    }

    public Page<Produto> listarTodosProdutos(Pageable pageable) {

        return produtoRepository.findAll(pageable);

    }

    public Produto buscarProduto(Long id) {

        Optional<Produto> optional = produtoRepository.findById(id);

        return optional.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public Produto atualizarProduto(Produto produto, Long id) {

        Produto produtoOriginal = buscarProduto(id);

        produto.setId(produtoOriginal.getId());

        return produtoRepository.save(produtoOriginal);
    }

    public void excluirProduto(Long id) {

        Produto produto = buscarProduto(id);

        produtoRepository.delete(produto);
    }
}
