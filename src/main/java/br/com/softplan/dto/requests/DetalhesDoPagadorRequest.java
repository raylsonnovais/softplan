package br.com.softplan.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DetalhesDoPagadorRequest {

    @NotNull(message = "O campo 'nome' é obrigatório")
    @Size(min = 1, max = 255, message = "O campo 'nome' deve conter entre 1 e 255 caracteres")
    private String nome;

    @NotNull(message = "O campo 'email' é obrigatório")
    @Size(min = 1, max = 255, message = "O campo 'email' deve conter entre 1 e 255 caracteres")
    private String email;

    private String tipoIdentidade;

    private String numeroIdentidade;

    @NotNull(message = "O campo 'pagamentoId' é obrigatório")
    private Long pagamentoId;
}

