package br.com.gft.services;

import br.com.gft.entities.Filial;
import br.com.gft.repositories.FilialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {

    private final FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {

        this.filialRepository = filialRepository;
    }

    public Filial salvarFilial(Filial filial) {

        return filialRepository.save(filial);
    }

    public List<Filial> listarTodasFiliais() {

        return filialRepository.findAll();
    }

    public Filial buscarFilial(Long id) {

        Optional<Filial> optional = filialRepository.findById(id);

        return optional.orElseThrow(() -> new RuntimeException("Filial não encontrada"));
    }

    public Filial atualizarFilial(Filial filial, Long id) {

        Filial filialOriginal = buscarFilial(id);

        filial.setId(filialOriginal.getId());

        return filialRepository.save(filial);
    }

    public void excluirFilial(Long id) {

        Filial filialOriginal = buscarFilial(id);

        filialRepository.delete(filialOriginal);

    }
}
