package br.com.softplan.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessaoRequest {

    @NotNull(message = "O campo 'idUsuario' é obrigatório")
    private Long idUsuario;

    @NotNull(message = "O campo 'token' é obrigatório")
    private String token;

    @NotNull(message = "O campo 'dataCriacao' é obrigatório")
    private LocalDateTime dataCriacao;

    @NotNull(message = "O campo 'dataExpiracao' é obrigatório")
    private LocalDateTime dataExpiracao;
}

