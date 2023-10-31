package br.com.softplan.dto.responses;

import lombok.Data;

@Data
public class MesaResponse {

    private Long id;
    private Long idDaFilial;
    private Integer numero;
    private String status;
}

