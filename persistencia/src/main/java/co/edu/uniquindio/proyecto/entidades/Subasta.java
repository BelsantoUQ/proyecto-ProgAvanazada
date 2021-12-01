package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Subasta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Future
    private LocalDateTime fechaLimite;

    @ManyToOne
    private Producto productoEnSubasta;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<Subasta_Usuario> subastasUsuarios;

    public Subasta(LocalDateTime fechaLimite, Producto productoEnSubasta) {
        this.fechaLimite = fechaLimite;
        this.productoEnSubasta = productoEnSubasta;
    }
}
