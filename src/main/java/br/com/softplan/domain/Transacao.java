package br.com.softplan.domain;

import br.com.softplan.enums.MetodoDePagamentoEnum;
import br.com.softplan.enums.StatusTransacaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_do_pedido", nullable = false)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodoDePagamento", nullable = false)
    private MetodoDePagamentoEnum metodoDePagamento;
    private LocalDateTime data;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusTransacaoEnum status;
}
