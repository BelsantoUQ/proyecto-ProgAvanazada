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
    private double precioProducto;

    @ManyToOne
    @ToString.Exclude
    private Producto productoCompra;

    @ManyToOne
    @ToString.Exclude
    private Compra compra;

    public DetalleCompra(Integer unidades, double precioProducto, Producto productoCompra, Compra compra) {
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.productoCompra = productoCompra;
        this.compra = compra;
    }
}
