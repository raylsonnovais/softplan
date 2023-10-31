package br.com.softplan.service;

import br.com.softplan.domain.Transacao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransacaoService {
    @Transactional
    Transacao criarTransacao(Transacao transacao);
    @Transactional
    Transacao atualizarTransacao(Long id, Transacao transacaoAtualizada);
    @Transactional
    void deletarTransacao(Long id);
    List<Transacao> listarTransacoes();
    Transacao obterTransacaoPorId(Long id);
}
