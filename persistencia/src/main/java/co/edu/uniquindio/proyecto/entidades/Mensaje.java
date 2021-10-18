package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Mensaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(length =100 ,nullable = false)
    private String mensaje;
    @Column(nullable = false)
    private String emisor;
    @Column(nullable = false)
    private LocalDateTime fehca;

    public Mensaje(String mensaje, String emisor, LocalDateTime fehca) {
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fehca = fehca;
    }
}
