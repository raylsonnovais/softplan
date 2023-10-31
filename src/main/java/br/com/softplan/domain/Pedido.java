package br.com.softplan.domain;

import br.com.softplan.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_da_mesa", nullable = false)
    private Mesa mesa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedidoEnum status;
    private LocalDateTime data;
    private BigDecimal total;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItensDoPedido> itensDoPedido = new ArrayList<>();

    public void adicionarItemAoPedido(ItensDoPedido item) {
        itensDoPedido.add(item);
        item.setPedido(this);
    }

    public void removerItemDoPedido(ItensDoPedido item) {
        itensDoPedido.remove(item);
        item.setPedido(null);
    }
}
