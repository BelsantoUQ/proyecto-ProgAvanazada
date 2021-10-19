package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Subasta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @ManyToOne
    private Producto productoEnSubasta;

    @OneToMany(mappedBy = "subastaUser")
    private List<Subasta_Usuario> subastasUsuarios;

    public Subasta(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
