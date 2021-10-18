package co.edu.uniquindio.proyecto.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Comentario implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Getter
    @Setter
    @Column(length = 50, nullable = false)
    private String mensaje;
    @Getter
    @Setter
    @Column(length = 50, nullable = false)
    private String respuesta;
    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime fechaComentario;
    @Getter
    @Setter
    private float calificacion;

    public Comentario(String mensaje, LocalDateTime fechaComentario) {
        this.mensaje = mensaje;
        this.fechaComentario = fechaComentario;
    }
}
