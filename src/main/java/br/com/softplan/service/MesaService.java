package br.com.softplan.service;

import br.com.softplan.domain.Mesa;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MesaService {
    @Transactional
    Mesa criarMesa(Mesa mesa);
    @Transactional
    Mesa atualizarMesa(Long id, Mesa mesaAtualizada);
    @Transactional
    void deletarMesa(Long id);
    List<Mesa> listarMesasPorFilial(Long filialId);
    Mesa obterMesaPorId(Long id);
}
