package br.com.softplan.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItensDoPedidoRequest {

    @NotNull(message = "O campo 'id_do_pedido' é obrigatório")
    private Long idDoPedido;

    @NotNull(message = "O campo 'id_do_item_menu' é obrigatório")
    private Long idDoItemMenu;

    @NotNull(message = "O campo 'quantidade' é obrigatório")
    private Integer quantidade;
}

