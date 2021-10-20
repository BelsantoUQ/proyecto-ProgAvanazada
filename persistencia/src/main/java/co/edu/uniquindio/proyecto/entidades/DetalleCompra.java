package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private float precioProducto;

    @ManyToOne
    @ToString.Exclude
    private Producto productoCompra;

    @ManyToOne
    @ToString.Exclude
    private Compra compra;


}
