package br.com.softplan.service.implementation;

import br.com.softplan.domain.LogDeAcao;
import br.com.softplan.repository.LogDeAcaoRepository;
import br.com.softplan.service.LogDeAcaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogDeAcaoServiceImpl implements LogDeAcaoService {

    private final LogDeAcaoRepository logDeAcaoRepository;

    public LogDeAcaoServiceImpl(LogDeAcaoRepository logDeAcaoRepository) {
        this.logDeAcaoRepository = logDeAcaoRepository;
    }

    @Override
    public List<LogDeAcao> findAll() {
        return logDeAcaoRepository.findAll();
    }

    @Override
    public Optional<LogDeAcao> findById(Long id) {
        return logDeAcaoRepository.findById(id);
    }

    @Override
    public LogDeAcao save(LogDeAcao logDeAcao) {
        return logDeAcaoRepository.save(logDeAcao);
    }

    @Override
    public LogDeAcao update(LogDeAcao logDeAcao) {
        return logDeAcaoRepository.save(logDeAcao);
    }

    @Override
    public void deleteById(Long id) {
        logDeAcaoRepository.deleteById(id);
    }
}
