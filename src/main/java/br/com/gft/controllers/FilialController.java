package br.com.gft.controllers;

import br.com.gft.dto.ConsultaFilialDTO;
import br.com.gft.dto.EnderecoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/filiais")
public class FilialController {

    @GetMapping
    public ResponseEntity<List<ConsultaFilialDTO>> buscarTodasAsFiliais() {

        EnderecoDTO endereco1 = new EnderecoDTO();
        endereco1.setLogradouro("Rua Um");

        EnderecoDTO endereco2 = new EnderecoDTO();
        endereco2.setLogradouro("Rua dois");

        ConsultaFilialDTO filial1 = new ConsultaFilialDTO();
        filial1.setNome("filial 1");
        filial1.setEndereco(endereco1);

        ConsultaFilialDTO filial2 = new ConsultaFilialDTO();
        filial2.setNome("filial 2");
        filial2.setEndereco(endereco2);

        List<ConsultaFilialDTO> lista = List.of(filial1, filial2);

        return ResponseEntity.ok(lista);
    }
}
