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
    private Float precio;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column(nullable = false)
    private Float descuento;

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
    private Usuario clienteUser;

    @ManyToOne
    private Ciudad ciudadProducto;

    @OneToMany(mappedBy = "productoCompra")
    private List<DetalleCompra> detallesDeCompras;

    @OneToMany(mappedBy = "comentarioProducto")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "productoEnSubasta")
    private  List<Subasta> subastas;

    public Producto(String codigo,
                    String nombre,
                    String descripcion,
                    Float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}
