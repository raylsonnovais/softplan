package br.com.softplan.dto.responses;

import br.com.softplan.domain.Filial;
import lombok.Data;

@Data
public class FilialResponse {
    private Long id;
    private String nome;
    private String localizacao;
    private String detalhes;

    public static FilialResponse fromFilial(Filial filial) {
        FilialResponse filialResponse = new FilialResponse();
        filialResponse.setId(filial.getId());
        filialResponse.setNome(filial.getNome());
        filialResponse.setLocalizacao(filial.getLocalizacao());
        filialResponse.setDetalhes(filial.getDetalhes());
        return filialResponse;
    }
}
