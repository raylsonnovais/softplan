package br.com.softplan.dto.requests;

import br.com.softplan.enums.AcaoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LogDeAcaoRequest {

    private Long idUsuario;

    @NotNull(message = "O campo 'acao' é obrigatório")
    private AcaoEnum acao;

    private String detalhes;

    @NotNull(message = "O campo 'dataHora' é obrigatório")
    private LocalDateTime dataHora;

    @NotNull(message = "O campo 'ip' é obrigatório")
    private String ip;
}

