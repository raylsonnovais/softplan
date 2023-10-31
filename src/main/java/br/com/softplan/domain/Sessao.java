package br.com.softplan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessões")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_do_usuario", referencedColumnName = "id")
    private Usuario usuario;

    private String token;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
}
