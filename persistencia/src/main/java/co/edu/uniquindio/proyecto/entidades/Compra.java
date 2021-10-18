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
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(nullable = false)
    private LocalDateTime fechaCompra;
    @Column(nullable = false)
    private String medioPago;

    public Compra(LocalDateTime fechaCompra, String medioPago) {
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
    }
}
