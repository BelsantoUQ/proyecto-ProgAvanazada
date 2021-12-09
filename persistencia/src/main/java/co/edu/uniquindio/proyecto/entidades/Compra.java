package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String medioPago;

    @ManyToOne
    private Usuario comprador;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> comprasDetalladas;

    public Compra(String medioPago, Usuario comprador) {
        this.medioPago = medioPago;
        this.comprador = comprador;
    }
}
