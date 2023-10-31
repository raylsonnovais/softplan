package br.com.softplan.service;

import br.com.softplan.domain.ItensDoPedido;
import br.com.softplan.domain.Mesa;
import br.com.softplan.domain.Pedido;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PedidoService {
    @Transactional
    Pedido criarPedido(Mesa mesa, List<ItensDoPedido> itens);
    @Transactional
    Pedido atualizarPedido(Pedido pedido, List<ItensDoPedido> itens);
    @Transactional
    void deletarPedido(Long id);
    List<Pedido> listarPedidosPorMesa(Long mesaId);
    Pedido obterPedidoPorId(Long id);
    List<ItensDoPedido> listarItensDoPedido(Long pedidoId);
    Pedido marcarPedidoComoConcluido(Pedido pedido);
}
