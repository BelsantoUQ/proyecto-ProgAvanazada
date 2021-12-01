package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Subasta_Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @PositiveOrZero
    private float valor;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Future
    private LocalDateTime fechaSubasta;

    @ManyToOne
    private Usuario usuarioSubasta;

    @ManyToOne
    private Subasta subasta;

    public Subasta_Usuario(float valor, Usuario usuarioSubasta, Subasta subasta) {
        this.valor = valor;
        this.usuarioSubasta = usuarioSubasta;
        this.subasta = subasta;
    }
}
