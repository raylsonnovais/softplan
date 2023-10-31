package br.com.softplan.dto.responses;

import br.com.softplan.domain.ItensDoPedido;
import br.com.softplan.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {

    private Long id;
    private Long idDaMesa;
    private String status;
    private LocalDateTime data;
    private List<ItensDoPedido> itensDoPedido;
    private BigDecimal total;

    public PedidoResponse(Pedido novoPedido) {
        this.id = novoPedido.getId();
        this.idDaMesa = novoPedido.getMesa().getId();
        this.status = novoPedido.getStatus().toString();
        this.data = novoPedido.getData();
        this.total = novoPedido.getTotal();
        this.itensDoPedido = novoPedido.getItensDoPedido();

    }
}

