package co.edu.uniquindio.proyecto.entidades;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 600)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column(nullable = false)
    private float descuento;

    @ElementCollection
    @Column(nullable = false, length = 500)
    private Map<String, String> imagenRuta;

  //  @JoinColumn(nullable = false)
    @ManyToMany(mappedBy = "productos")
    private List<Categoria> categorias;

//    @JoinColumn(nullable = false)
    @ManyToMany(mappedBy = "productosFavoritos")
    private List<Usuario> usuariosPotenciales;

    @ManyToOne
    private Usuario vendedor;

    @ManyToOne
    private Ciudad ciudadProducto;

    @OneToMany(mappedBy = "productoCompra")
    @ToString.Exclude
    private List<DetalleCompra> detallesDeCompras;

    @OneToMany(mappedBy = "productoC")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "productoEnSubasta")
    @ToString.Exclude
    private  List<Subasta> subastas;

    public Producto(String codigo, String nombre, Integer unidades, String descripcion,
                    double precio, LocalDate fechaLimite, float descuento, Map<String, String> imagenRuta,
                    List<Categoria> categorias, Usuario vendedor, Ciudad ciudadProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.descuento = descuento;
        this.imagenRuta = imagenRuta;
        this.categorias = categorias;
        this.vendedor = vendedor;
        this.ciudadProducto = ciudadProducto;
    }
}
