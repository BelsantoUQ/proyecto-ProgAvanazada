package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(unique = true,nullable = false, length = 100)
    private String username;

    @Column(length = 300)
    private String rutaFoto;
    
    @ElementCollection()
    private List<String> num_telefono = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadUsuario;

    public Usuario(String nombre, String username, String email, String password, List<String> num_telefono, Ciudad ciudad, int puntos) {
        super(nombre, email, password);
        this.username = username;
        this.num_telefono = num_telefono;
        this.ciudadUsuario = ciudad;
        this.puntos = puntos;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "userComent", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comentario> comentariosDelUser;

    @OneToMany(mappedBy = "usuarioSubasta",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Subasta_Usuario> subastasDeUsuario;

    @OneToMany(mappedBy = "comprador",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Compra> comprasUsuario;

    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Producto> productosVenta;

    @OneToMany(mappedBy = "chatUsuario",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Chat> chatsDelUsuario;

    public String getImagenUser(){
        if(this.rutaFoto !=null && !this.rutaFoto.equals("")){

            return this.rutaFoto;

        }
        return "default2.png";
    }



}
