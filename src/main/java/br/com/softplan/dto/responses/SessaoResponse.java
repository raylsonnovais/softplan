package br.com.softplan.dto.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoResponse {

    private Long id;
    private Long idUsuario;
    private String token;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
}

