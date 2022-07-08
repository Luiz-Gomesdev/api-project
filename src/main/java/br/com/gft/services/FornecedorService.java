package br.com.gft.services;

import br.com.gft.entities.Fornecedor;
import br.com.gft.exception.EntityNotFoundException;
import br.com.gft.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {

        return fornecedorRepository.save(fornecedor);

    }

    public Page<Fornecedor> listarTodosFornecedores(Pageable pageable) {

        return fornecedorRepository.findAll(pageable);
    }

    public Fornecedor buscarFornecedor(Long id) {

        Optional<Fornecedor> optional = fornecedorRepository.findById(id);

        return optional.orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    public Fornecedor atualizarFornecedor(Fornecedor fornecedor, Long id) {

        Fornecedor fornecedorOriginal = buscarFornecedor(id);

        fornecedor.setId(fornecedorOriginal.getId());

        return fornecedorRepository.save(fornecedorOriginal);
    }

    public void excluirFornecedor(Long id) {

        Fornecedor fornecedorOriginal = buscarFornecedor(id);

        fornecedorRepository.delete(fornecedorOriginal);
    }


}
