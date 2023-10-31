package br.com.softplan.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagamentoRequest {

    @NotNull(message = "O campo 'status' é obrigatório")
    private String status;

    private String descricao;

    @NotNull(message = "O campo 'dataDeCriacao' é obrigatório")
    private LocalDateTime dataDeCriacao;

    private LocalDateTime dataDeAprovacao;

    @NotNull(message = "O campo 'valorTotal' é obrigatório")
    private BigDecimal valorTotal;

    @NotNull(message = "O campo 'metodoDePagamentoId' é obrigatório")
    private String metodoDePagamentoId;

    @NotNull(message = "O campo 'metodoDePagamentoNome' é obrigatório")
    private String metodoDePagamentoNome;

    @NotNull(message = "O campo 'pedidoId' é obrigatório")
    private Long pedidoId;
}

