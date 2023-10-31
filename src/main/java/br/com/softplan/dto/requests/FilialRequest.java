package br.com.softplan.dto.requests;

import br.com.softplan.domain.Filial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class FilialRequest {
    @NotBlank(message = "O campo 'nome' é obrigatório")
    @Size(max = 255, message = "O campo 'nome' deve ter no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "O campo 'localizacao' é obrigatório")
    @Size(max = 255, message = "O campo 'localizacao' deve ter no máximo 255 caracteres")
    private String localizacao;

    @Size(max = 500, message = "O campo 'detalhes' deve ter no máximo 500 caracteres")
    private String detalhes;

    public Filial toFilial() {
        Filial filial = new Filial();
        filial.setNome(this.nome);
        filial.setLocalizacao(this.localizacao);
        filial.setDetalhes(this.detalhes);
        return filial;

    }
}
