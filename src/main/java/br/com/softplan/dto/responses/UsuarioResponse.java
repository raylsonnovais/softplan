package br.com.softplan.dto.responses;

import br.com.softplan.enums.StatusUsuarioEnum;
import br.com.softplan.enums.TipoUsuarioEnum;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    private TipoUsuarioEnum tipo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private StatusUsuarioEnum status;
}

