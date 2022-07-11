package br.com.gft.controllers;

import br.com.gft.dto.cliente.ClienteMapper;
import br.com.gft.dto.cliente.ConsultaClienteDTO;
import br.com.gft.dto.cliente.RegistroClienteDTO;
import br.com.gft.entities.Cliente;
import br.com.gft.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<ConsultaClienteDTO>> buscarTodosOsClientes(@PageableDefault Pageable pageable){

        return ResponseEntity.ok(clienteService.listarTodosClientes(pageable).map(ClienteMapper::fromEntity));
    }

    @PostMapping
    public ResponseEntity<ConsultaClienteDTO> salvarCliente(@RequestBody RegistroClienteDTO dto) {

        Cliente cliente = clienteService.salvarCliente(ClienteMapper.fromDTO(dto));

        return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaClienteDTO> alterarCliente(@RequestBody RegistroClienteDTO dto,
                                                             @PathVariable Long id) {

        try {
            Cliente cliente = clienteService.atualizarCliente(ClienteMapper.fromDTO(dto), id);

            return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaClienteDTO> excluirCliente(@PathVariable Long id) {

        try {
            clienteService.excluirCliente(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
