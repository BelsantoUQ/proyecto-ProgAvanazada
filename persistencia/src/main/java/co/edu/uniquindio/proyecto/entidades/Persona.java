package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@ToString(callSuper = true)
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    @Length(max = 100, message = "Debe ser menor a 100 caracteres")
    @NotBlank
    private String nombre;

    @Column(unique = true,nullable = false, length = 200)
    @Email(message = "Escriba un email válido")
    @NotBlank
    private String email;

    @Column(nullable = false, length = 50)
    @Length(max = 50)
    @NotBlank
    @ToString.Exclude
    private String password;


    public Persona(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
}