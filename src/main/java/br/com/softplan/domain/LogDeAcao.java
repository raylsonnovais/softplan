package br.com.softplan.domain;

import br.com.softplan.enums.AcaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs_de_acoes")
public class LogDeAcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_do_usuario", referencedColumnName = "id")
    private Usuario usuario;

    private String acao;
    private String detalhes;
    private LocalDateTime dataHora;
    private String ip;
}
