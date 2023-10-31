package br.com.softplan.service;

import br.com.softplan.domain.Sessao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SessaoService {
    @Transactional
    Sessao criarSessao(Long usuarioId);
    void encerrarSessao(Long sessaoId);
    Sessao obterSessaoPorToken(String token);
}
