package br.com.softplan.service.implementation;

import br.com.softplan.domain.ItensDoPedido;
import br.com.softplan.domain.Mesa;
import br.com.softplan.domain.Pedido;
import br.com.softplan.enums.StatusMesaEnum;
import br.com.softplan.enums.StatusPedidoEnum;
import br.com.softplan.repository.ItensDoPedidoRepository;
import br.com.softplan.repository.MesaRepository;
import br.com.softplan.repository.PedidoRepository;
import br.com.softplan.service.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ItensDoPedidoRepository itensDoPedidoRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    @Transactional
    public Pedido criarPedido(Mesa mesa, List<ItensDoPedido> itens) {
        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setStatus(StatusPedidoEnum.PENDENTE);

        mesa.setStatus(StatusMesaEnum.OCUPADA);
        mesaRepository.save(mesa);

        pedido = pedidoRepository.save(pedido);


        BigDecimal totalDoPedido = itens.stream()
                .map(item -> item.getItemMenu().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setTotal(totalDoPedido);

        for (ItensDoPedido item : itens) {
            item.setPedido(pedido);
            itensDoPedidoRepository.save(item);
        }

        return pedido;
    }

    @Override
    @Transactional
    public Pedido atualizarPedido(Pedido pedido, List<ItensDoPedido> itens) {
        itensDoPedidoRepository.deleteByPedidoId(pedido.getId());


        for (ItensDoPedido item : itens) {
            item.setPedido(pedido);
            itensDoPedidoRepository.save(item);
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public void deletarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {

            Mesa mesa = pedido.getMesa();
            mesa.setStatus(StatusMesaEnum.DISPONIVEL);
            messagingTemplate.convertAndSend("/topic/mesaVaga", mesa);
            mesaRepository.save(mesa);

            pedidoRepository.deleteById(id);
        }
    }

    @Override
    public List<Pedido> listarPedidosPorMesa(Long mesaId) {

        return pedidoRepository.findByMesaId(mesaId);
    }

    @Override
    public Pedido obterPedidoPorId(Long id) {

        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<ItensDoPedido> listarItensDoPedido(Long pedidoId) {

        return itensDoPedidoRepository.findByPedidoId(pedidoId);
    }

    @Override
    @Transactional
    public Pedido marcarPedidoComoConcluido(Pedido pedido) {
        pedido.setStatus(StatusPedidoEnum.PAGO);

        Mesa mesa = pedido.getMesa();

        mesa.setStatus(StatusMesaEnum.DISPONIVEL);
        messagingTemplate.convertAndSend("/topic/mesaVaga", mesa);
        mesaRepository.save(mesa);

        return pedidoRepository.save(pedido);
    }
}

