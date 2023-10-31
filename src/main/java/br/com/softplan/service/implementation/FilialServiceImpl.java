package br.com.softplan.service.implementation;

import br.com.softplan.domain.Filial;
import br.com.softplan.domain.LogDeAcao;
import br.com.softplan.repository.FilialRepository;
import br.com.softplan.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FilialServiceImpl implements FilialService {

    private final FilialRepository filialRepository;

    @Autowired
    public FilialServiceImpl(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    @Override
    @Transactional
    public Filial criarFilial(Filial filial) {
        return filialRepository.save(filial);
    }

    @Override
    @Transactional
    public Filial atualizarFilial(Long id, Filial filialAtualizada) {
        if(!filialRepository.existsById(id)) {
            throw new IllegalArgumentException("Filial com ID " + id + " não encontrada.");
        }
        filialAtualizada.setId(id);
        return filialRepository.save(filialAtualizada);
    }

    @Override
    @Transactional
    public void deletarFilial(Long id) {
        if(!filialRepository.existsById(id)) {
            throw new IllegalArgumentException("Filial com ID " + id + " não encontrada.");
        }
        filialRepository.deleteById(id);
    }

    @Override
    public List<Filial> listarFiliais() {
        return filialRepository.findAll();
    }

    @Override
    public Filial obterFilialPorId(Long id) {
        Optional<Filial> optionalFilial = filialRepository.findById(id);
        return optionalFilial.orElseThrow(() -> new IllegalArgumentException("Filial com ID " + id + " não encontrada."));
    }
}
