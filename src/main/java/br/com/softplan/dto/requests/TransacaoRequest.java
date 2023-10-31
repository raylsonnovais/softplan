package br.com.softplan.dto.requests;

import br.com.softplan.enums.MetodoDePagamentoEnum;
import br.com.softplan.enums.StatusTransacaoEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoRequest {

    @NotNull(message = "O campo 'id_do_pedido' é obrigatório")
    private Long idDoPedido;

    @NotNull(message = "O campo 'metodoDePagamento' é obrigatório")
    private MetodoDePagamentoEnum metodoDePagamento;

    @NotNull(message = "O campo 'data' é obrigatório")
    private LocalDateTime data;

    @NotNull(message = "O campo 'total' é obrigatório")
    @DecimalMin(value = "0.01", message = "O campo 'total' deve ser maior que zero")
    private BigDecimal total;

    @NotNull(message = "O campo 'status' é obrigatório")
    private StatusTransacaoEnum status;
}

