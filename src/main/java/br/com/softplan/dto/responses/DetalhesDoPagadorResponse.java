package br.com.softplan.dto.responses;

import lombok.Data;

@Data
public class DetalhesDoPagadorResponse {

    private Long id;
    private String nome;
    private String email;
    private String tipoIdentidade;
    private String numeroIdentidade;
    private Long pagamentoId;
}

