package br.com.softplan.dto.requests;

import br.com.softplan.domain.ItemMenu;
import br.com.softplan.enums.TipoItemMenuEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemMenuRequest {

    @NotBlank(message = "O campo 'nome' é obrigatório")
    @Size(max = 255, message = "O campo 'nome' deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(message = "O campo 'preco' é obrigatório")
    @Positive(message = "O campo 'preco' deve ser um número positivo")
    private BigDecimal preco;

    @NotBlank(message = "O campo 'descricao' é obrigatório")
    @Size(max = 500, message = "O campo 'descricao' deve ter no máximo 500 caracteres")
    private String descricao;

    @NotBlank(message = "O campo 'tipo' é obrigatório")
    @Size(max = 10, message = "O campo 'tipo' deve ter no máximo 10 caracteres")
    private TipoItemMenuEnum tipo;

    public ItemMenu toItemMenu() {
        ItemMenu itemMenu = new ItemMenu();
        itemMenu.setNome(this.nome);
        itemMenu.setPreco(this.preco);
        itemMenu.setDescricao(this.descricao);
        itemMenu.setTipo(this.tipo);
        return itemMenu;
    }
}

