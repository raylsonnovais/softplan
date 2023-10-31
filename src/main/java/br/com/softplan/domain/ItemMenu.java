package br.com.softplan.domain;

import br.com.softplan.enums.TipoItemMenuEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_menu")
public class ItemMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoItemMenuEnum tipo;
}
