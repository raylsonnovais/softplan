package br.com.softplan.service;

import br.com.softplan.domain.LogDeAcao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LogDeAcaoService {
    List<LogDeAcao> findAll();
    Optional<LogDeAcao> findById(Long id);
    @Transactional
    LogDeAcao save(LogDeAcao logDeAcao);
    @Transactional
    LogDeAcao update(LogDeAcao logDeAcao);
    @Transactional
    void deleteById(Long id);
}
