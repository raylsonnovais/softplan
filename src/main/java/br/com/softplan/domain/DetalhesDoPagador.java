package br.com.softplan.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "detalhes_do_pagador")
public class DetalhesDoPagador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @Column(name = "tipo_identidade")
    private String tipoIdentidade;

    @Column(name = "numero_identidade")
    private String numeroIdentidade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;
}