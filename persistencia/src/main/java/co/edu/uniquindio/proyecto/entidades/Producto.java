package co.edu.uniquindio.proyecto.entidades;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Positive
    private String codigo;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String nombre;

    @Column(nullable = false, length = 80)
    private String nombrePublicacion;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer unidades;

    @Column(nullable = false, length = 600)
    @NotBlank
    private String descripcion;

    @PositiveOrZero
    private int valor_en_puntos;

    @Column(nullable = false)
    @Positive
    private float precio;

    @Column(nullable = false)
    @Future
    private LocalDate fechaLimite;

    @PositiveOrZero
    private float descuento;


    @ElementCollection()
    @Fetch(FetchMode.SUBSELECT)
    private List<String> imagenRuta;

    @ElementCollection()
    @Fetch(FetchMode.SUBSELECT)
    private List<Categoria> categorias;

//    @JoinColumn(nullable = false)
    @ManyToMany(mappedBy = "productosFavoritos")
    @ToString.Exclude
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

    public Producto(String codigo, String nombre, Integer unidades, String descripcion, int valor_en_puntos,
                    float precio, LocalDate fechaLimite, float descuento,
                    List<Categoria> categorias, Usuario vendedor, Ciudad ciudadProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.descuento = descuento;
        this.imagenRuta = new ArrayList<>();
        this.categorias = categorias;
        this.vendedor = vendedor;
        this.ciudadProducto = ciudadProducto;
        this.valor_en_puntos = valor_en_puntos;
        this.usuariosPotenciales = new ArrayList<>();
    }

    public String getImagenDestacada(){
        if(this.imagenRuta !=null && !this.imagenRuta.isEmpty()){

            return this.imagenRuta.get(0);

        }
        return "default.png";
    }
}
