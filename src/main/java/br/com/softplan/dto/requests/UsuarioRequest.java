package br.com.softplan.dto.requests;

import br.com.softplan.enums.StatusUsuarioEnum;
import br.com.softplan.enums.TipoUsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioRequest {

    @NotNull(message = "O campo 'nome' é obrigatório")
    private String nome;

    @NotNull(message = "O campo 'email' é obrigatório")
    @Email(message = "O campo 'email' deve ser um endereço de email válido")
    private String email;

    @NotNull(message = "O campo 'senhaHash' é obrigatório")
    private String senhaHash;

    @NotNull(message = "O campo 'tipo' é obrigatório")
    private TipoUsuarioEnum tipo;

    @NotNull(message = "O campo 'dataCriacao' é obrigatório")
    private LocalDateTime dataCriacao;

    @NotNull(message = "O campo 'dataAtualizacao' é obrigatório")
    private LocalDateTime dataAtualizacao;

    @NotNull(message = "O campo 'status' é obrigatório")
    private StatusUsuarioEnum status;
}

