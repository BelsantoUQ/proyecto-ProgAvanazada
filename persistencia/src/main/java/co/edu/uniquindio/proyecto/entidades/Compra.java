package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCompra;

    @Column(nullable = false)
    private String medioPago;

    @ManyToOne
    private Usuario compraDeUser;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> comprasDetalladas;

    public Compra(String medioPago) {

        this.medioPago = medioPago;
    }
}
