package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    private Usuario chatUsuario;

    @ManyToOne
    private  Producto chatProducto;

    @OneToMany(mappedBy = "chatPrincipal")
    @ToString.Exclude
    @JsonIgnore
    private List<Mensaje> mensajesDelChat;

    public Chat(Usuario chatUsuario, Producto chatProducto) {
        this.chatUsuario = chatUsuario;
        this.chatProducto = chatProducto;
    }
}
