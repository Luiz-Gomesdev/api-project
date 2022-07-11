package br.com.gft.services;

import br.com.gft.entities.Cliente;
import br.com.gft.exception.EntityNotFoundException;
import br.com.gft.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente salvarCliente (Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    public Page<Cliente> listarTodosClientes(Pageable pageable) {

        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarCliente(Long id) {

        Optional<Cliente> optional = clienteRepository.findById(id);

        return optional.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Cliente atualizarCliente(Cliente cliente, Long id) {

        Cliente clienteOriginal = buscarCliente(id);

        cliente.setId(clienteOriginal.getId());

        return clienteRepository.save(clienteOriginal);
    }

    public void excluirCliente (Long id) {

        Cliente cliente = buscarCliente(id);

        clienteRepository.delete(cliente);
    }
}
