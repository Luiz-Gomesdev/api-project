package br.com.gft.controllers;

import br.com.gft.dto.filial.ConsultaFilialDTO;
import br.com.gft.dto.filial.FilialMapper;
import br.com.gft.dto.filial.RegistroFilialDTO;
import br.com.gft.entities.Filial;
import br.com.gft.services.FilialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/filiais")
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaFilialDTO>> buscarTodasAsFiliais() {

        return ResponseEntity.ok(filialService.listarTodasFiliais()
                .stream().map(FilialMapper::fromEntity).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ConsultaFilialDTO> salvarFilial(@RequestBody RegistroFilialDTO dto) {

       Filial filial = filialService.salvarFilial(FilialMapper.fromDTO(dto));

        return ResponseEntity.ok(FilialMapper.fromEntity(filial));
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> buscarFilial(@PathVariable Long id) {

        try {
            Filial filial = filialService.buscarFilial(id);

            return ResponseEntity.ok(FilialMapper.fromEntity(filial));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> alterarFilial(@RequestBody RegistroFilialDTO dto,
                                                           @PathVariable Long id) {
        try {
            Filial filial = filialService.atualizarFilial(FilialMapper.fromDTO(dto), id);

            return ResponseEntity.ok(FilialMapper.fromEntity(filial));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> excluirFilial(@PathVariable Long id) {

        try {
            filialService.excluirFilial(id);

            return ResponseEntity.ok().build();

        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
