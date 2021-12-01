package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Mensaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length =300 ,nullable = false)
    @Length(max = 300, message = "El mensaje debe ser menor a 300 caracteres")
    private String mensaje;

    @Column(nullable = false)
    @NotBlank
    private String emisor;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fehca;

    @ManyToOne
    private Chat chatPrincipal;

    public Mensaje(String mensaje, String emisor, Chat chatPrincipal) {
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.chatPrincipal = chatPrincipal;
    }
}
