package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {


    @Column(nullable = false, length = 100)
    private int puntos;
    
    @ElementCollection
    @Column(nullable = false)
    private Map<String,String> num_telefono;


    @ElementCollection
    @Column(nullable = false, length = 500)
    private Map<String, String> rutaFoto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadUsuario;

    public Usuario(String nombre, String email, String password, Map<String, String> num_telefono, Ciudad ciudad, int puntos) {
        super(nombre, email, password);
        this.num_telefono = num_telefono;
        this.ciudadUsuario = ciudad;
        this.puntos = puntos;
    }

    @ManyToMany
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "userComent")
    @ToString.Exclude
    private List<Comentario> comentariosDelUser;

    @OneToMany(mappedBy = "usuarioSubasta")
    @ToString.Exclude
    private List<Subasta_Usuario> subastasDeUsuario;

    @OneToMany(mappedBy = "compraDeUser")
    @ToString.Exclude
    private List<Compra> comprasUsuario;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Producto> productosVenta;

    @OneToMany(mappedBy = "chatUsuario")
    @ToString.Exclude
    private List<Chat> chatsDelUsuario;



}
