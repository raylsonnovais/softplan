package br.com.softplan.repository;

import br.com.softplan.domain.ItensDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensDoPedidoRepository extends JpaRepository<ItensDoPedido, Long> {
    List<ItensDoPedido> findByPedidoId(Long pedidoId);
    void deleteByPedidoId(Long pedidoId);

}
