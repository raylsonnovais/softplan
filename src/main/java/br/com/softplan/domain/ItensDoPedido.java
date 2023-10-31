package br.com.softplan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens_do_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensDoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_do_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_do_item_menu", nullable = false)
    private ItemMenu itemMenu;

    private int quantidade;
}
