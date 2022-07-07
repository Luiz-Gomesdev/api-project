package br.com.gft.services;

import br.com.gft.entities.Filial;
import br.com.gft.repositories.FilialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
