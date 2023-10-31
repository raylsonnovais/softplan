package br.com.softplan.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    private String descricao;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao;

    @Column(name = "data_de_aprovacao")
    private LocalDateTime dataDeAprovacao;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "metodo_de_pagamento_id")
    private String metodoDePagamentoId;

    @Column(name = "metodo_de_pagamento_nome")
    private String metodoDePagamentoNome;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DetalhesDoPagador detalhesDoPagador;

    @ManyToOne // Associação com Pedido
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
