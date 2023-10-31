package br.com.softplan.dto.responses;

import br.com.softplan.domain.ItemMenu;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemMenuResponse {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String tipo;

    public static ItemMenuResponse fromItemMenu(ItemMenu itemMenu) {
        ItemMenuResponse itemMenuResponse = new ItemMenuResponse();
        itemMenuResponse.setId(itemMenu.getId());
        itemMenuResponse.setNome(itemMenu.getNome());
        itemMenuResponse.setPreco(itemMenu.getPreco());
        itemMenuResponse.setDescricao(itemMenu.getDescricao());
        itemMenuResponse.setTipo(String.valueOf(itemMenu.getTipo()));
        return itemMenuResponse;
    }
}

