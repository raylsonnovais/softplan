package br.com.softplan.dto.requests;

import br.com.softplan.enums.StatusMesaEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class MesaRequest {

    @NotNull(message = "O campo 'idDaFilial' é obrigatório")
    @Positive(message = "O campo 'idDaFilial' deve ser um número positivo")
    private Long idDaFilial;

    @NotNull(message = "O campo 'numero' é obrigatório")
    @Positive(message = "O campo 'numero' deve ser um número positivo")
    private Integer numero;

    @Size(max = 50, message = "O campo 'status' deve ter no máximo 50 caracteres")
    private StatusMesaEnum status;
}

