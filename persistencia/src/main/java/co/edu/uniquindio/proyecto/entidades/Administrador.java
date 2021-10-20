package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable{

    public Administrador(String nombre, String email, String password) {
        super(nombre, email, password);
    }
}
