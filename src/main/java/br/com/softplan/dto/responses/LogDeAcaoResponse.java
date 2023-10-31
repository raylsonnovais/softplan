package br.com.softplan.dto.responses;

import br.com.softplan.enums.AcaoEnum;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class LogDeAcaoResponse {
    private Long id;
    private Long idUsuario;
    private AcaoEnum acao;
    private String detalhes;
    private LocalDateTime dataHora;
    private String ip;
}
