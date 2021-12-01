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
@ToString(callSuper = true)
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 50, nullable = false)
    @Length(max = 50, message ="Debe ser menor de 50 caracteres" )
    private String nombre;

    @OneToMany(mappedBy = "ciudadUsuario")
    @ToString.Exclude
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ciudadProducto")
    @ToString.Exclude
    private List<Producto> productos;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
