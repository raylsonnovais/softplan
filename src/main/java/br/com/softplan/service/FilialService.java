package br.com.softplan.service;

import br.com.softplan.domain.Filial;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FilialService {
    @Transactional
    Filial criarFilial(Filial filial);
    @Transactional
    Filial atualizarFilial(Long id, Filial filialAtualizada);
    @Transactional
    void deletarFilial(Long id);
    List<Filial> listarFiliais();
    Filial obterFilialPorId(Long id);
}
