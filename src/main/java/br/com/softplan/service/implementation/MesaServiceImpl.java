package br.com.softplan.service.implementation;


import br.com.softplan.domain.Mesa;
import br.com.softplan.domain.Pedido;
import br.com.softplan.enums.StatusMesaEnum;
import br.com.softplan.enums.StatusPedidoEnum;
import br.com.softplan.repository.MesaRepository;
import br.com.softplan.repository.PedidoRepository;
import br.com.softplan.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public MesaServiceImpl(MesaRepository mesaRepository, PedidoRepository pedidoRepository) {
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public Mesa criarMesa(Mesa mesa) {
        mesa.setStatus(StatusMesaEnum.DISPONIVEL);
        return mesaRepository.save(mesa);
    }

    @Override
    @Transactional
    public Mesa atualizarMesa(Long id, Mesa mesaAtualizada) {
        Mesa mesaExistente = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa com ID " + id + " não encontrada."));

        if(mesaExistente.getStatus() == StatusMesaEnum.OCUPADA && mesaAtualizada.getStatus() == StatusMesaEnum.DISPONIVEL) {
            List<Pedido> pedidosDaMesa = pedidoRepository.findByMesaId(id);
            boolean todosPagos = pedidosDaMesa.stream().allMatch(pedido -> pedido.getStatus() == StatusPedidoEnum.PAGO);

            if (!todosPagos) {
                throw new IllegalStateException("Não é possível liberar a mesa enquanto existem pedidos pendentes.");
            }
        }

        mesaAtualizada.setId(id);
        return mesaRepository.save(mesaAtualizada);
    }

    @Override
    @Transactional
    public void deletarMesa(Long id) {
        if(!mesaRepository.existsById(id)) {
            throw new IllegalArgumentException("Mesa com ID " + id + " não encontrada.");
        }
        mesaRepository.deleteById(id);
    }

    @Override
    public List<Mesa> listarMesasPorFilial(Long filialId) {
        return mesaRepository.findByFilialId(filialId);
    }

    @Override
    public Mesa obterMesaPorId(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa com ID " + id + " não encontrada."));
    }
}
