package br.com.softplan.service;

import br.com.softplan.domain.ItensDoPedido;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ItemDoPedidoService {
    List<ItensDoPedido> findAll();
    Optional<ItensDoPedido> findById(Long id);
    @Transactional
    ItensDoPedido save(ItensDoPedido itemDoPedido);
    @Transactional
    ItensDoPedido update(ItensDoPedido itemDoPedido);
    @Transactional
    void deleteById(Long id);
}
