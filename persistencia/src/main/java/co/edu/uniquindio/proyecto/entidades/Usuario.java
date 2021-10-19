package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    
    @ElementCollection
    @Column(nullable = false)
    private List<String> num_telefono;

    //@JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudadUsuario;

    @ManyToMany
    //@JoinColumn(nullable = false)
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "userComent")
    private List<Comentario> comentariosDelUser;

    @OneToMany(mappedBy = "usuarioSubasta")
    private List<Subasta_Usuario> subastasDeUsuario;

    @OneToMany(mappedBy = "compraDeUser")
    private List<Compra> comprasUsuario;

    @OneToMany(mappedBy = "clienteUser")
    private List<Producto> productosUsuario;

    @OneToMany(mappedBy = "chatUsuario")
    private List<Chat> chatsDelUsuario;

}
