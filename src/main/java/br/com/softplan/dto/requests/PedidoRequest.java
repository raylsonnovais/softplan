package br.com.softplan.dto.requests;

import br.com.softplan.domain.ItensDoPedido;
import br.com.softplan.enums.StatusPedidoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoRequest {

    @NotNull(message = "O campo 'id_da_mesa' é obrigatório")
    private Long idDaMesa;

    @NotNull(message = "O campo 'status' é obrigatório")
    private StatusPedidoEnum status;
    private List<ItensDoPedido> itensDoPedido;

    private BigDecimal total;
}

