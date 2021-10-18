package co.edu.uniquindio.proyecto.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String mensaje;
    @Getter
    @Setter
    private String respuesta;
    @Getter
    @Setter
    private LocalDateTime fechaComentario;
    @Getter
    @Setter
    private float calificacion;

    public Comentario(String mensaje, LocalDateTime fechaComentario) {
        this.mensaje = mensaje;
        this.fechaComentario = fechaComentario;
    }
}
