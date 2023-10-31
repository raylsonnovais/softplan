package br.com.softplan.dto.responses;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagamentoResponse {

    private Long id;
    private String status;
    private String descricao;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAprovacao;
    private BigDecimal valorTotal;
    private String metodoDePagamentoId;
    private String metodoDePagamentoNome;
    private Long pedidoId;
}

