package br.com.softplan.domain;

import br.com.softplan.enums.StatusUsuarioEnum;
import br.com.softplan.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUsuarioEnum tipo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusUsuarioEnum status;
}
