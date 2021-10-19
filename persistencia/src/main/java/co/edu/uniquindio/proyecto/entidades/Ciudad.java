package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 50, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ciudadUsuario")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ciudadProducto")
    private List<Producto> productos;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
