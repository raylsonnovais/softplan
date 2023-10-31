package br.com.softplan.domain;

import br.com.softplan.enums.StatusMesaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mesas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_da_filial", nullable = false)
    private Filial filial;

    private int numero;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMesaEnum status;
}
