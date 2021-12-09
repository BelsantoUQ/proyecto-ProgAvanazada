package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {


    @Column(nullable = false, length = 100)
    private int puntos;

    @Column(unique = true,nullable = false, length = 100)
    private String username;

    @Column(length = 300)
    private String rutaFoto;
    
    @ElementCollection()
    private List<String> num_telefono;

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
    @ToString.Exclude
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "userComent")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentariosDelUser;

    @OneToMany(mappedBy = "usuarioSubasta")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta_Usuario> subastasDeUsuario;

    @OneToMany(mappedBy = "comprador")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> comprasUsuario;

    @OneToMany(mappedBy = "vendedor")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productosVenta;

    @OneToMany(mappedBy = "chatUsuario")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chatsDelUsuario;

    public Usuario(){

        this.num_telefono = new ArrayList<>();
        this.productosFavoritos = new ArrayList<>();
        this.chatsDelUsuario = new ArrayList<>();
        this.productosVenta = new ArrayList<>();
        this.comprasUsuario = new ArrayList<>();
        this.subastasDeUsuario = new ArrayList<>();
        this.comentariosDelUser = new ArrayList<>();
        this.productosFavoritos = new ArrayList<>();

    }

    public String getImagenUser(){
        if(this.rutaFoto !=null && !this.rutaFoto.equals("")){

            return this.rutaFoto;

        }
        return "default2.png";
    }



}
