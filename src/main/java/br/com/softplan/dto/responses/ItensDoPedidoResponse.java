package br.com.softplan.dto.responses;

import lombok.Data;

@Data
public class ItensDoPedidoResponse {

    private Long id;
    private Long idDoPedido;
    private Long idDoItemMenu;
    private Integer quantidade;
}

