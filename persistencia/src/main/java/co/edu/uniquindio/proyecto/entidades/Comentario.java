package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Comentario implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 50, nullable = false)
    private String mensaje;

    @Column(length = 50, nullable = false)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @Column(precision = 4)
    private float calificacion;

    @ManyToOne
    private Usuario userComent;

    @ManyToOne
    private Producto comentarioProducto;


    public Comentario(String mensaje ) {
        this.mensaje = mensaje;this.fechaComentario = fechaComentario;
    }
}
