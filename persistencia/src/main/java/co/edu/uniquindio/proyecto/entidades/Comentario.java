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

    @Column(length = 300, nullable = false)
    private String mensaje;

    @Column(length = 300, nullable = false)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @Column(nullable = false)
    private int calificacion;

    @ManyToOne
    private Usuario userComent;

    @ManyToOne
    private Producto productoC;

    public Comentario(String mensaje, String respuesta, int calificacion, Usuario userComent, Producto productoC) {
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = LocalDateTime.now();
        this.calificacion = calificacion;
        this.userComent = userComent;
        this.productoC = productoC;
    }
}
