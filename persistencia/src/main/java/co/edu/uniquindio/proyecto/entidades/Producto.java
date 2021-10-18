package co.edu.uniquindio.proyecto.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, precision = 3)
    private Integer unidades;
    @Column(nullable = false, length = 600)
    private String descripcion;
    @Column(nullable = false)
    private Float precio;
    @Column(nullable = false)
    private LocalDate fechaLimite;
    @Column(nullable = false)
    private Float descuento;

    @ElementCollection
    @Column(nullable = false, length = 500)
    private List<String> imagenRuta;

    public Producto(String codigo,
                    String nombre,
                    String descripcion,
                    Float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}
