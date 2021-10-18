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
public class Subasta_Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(nullable = false)
    private float valor;
    @Column(nullable = false)
    private LocalDateTime fechaSubasta;

    public Subasta_Usuario(float valor, LocalDateTime fechaSubasta) {
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
    }
}
