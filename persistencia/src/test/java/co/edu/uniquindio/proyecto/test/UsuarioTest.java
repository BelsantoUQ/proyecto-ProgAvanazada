package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1001).orElse(null);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("personal","3165411222");
        telefonos.put("la novia","3175412687");
        Usuario usuario = new Usuario("Sergio Esteban","sergiesito@gmail.com","12345",telefonos, ciudad);


        Usuario usuarioGuardado1 = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado1);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById(101);

        Usuario usuarioBuscado = usuarioRepo.findById(101).orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarTest(){

        Usuario guardado = usuarioRepo.findById(100).orElse(null);

        //Modifico el email de usuario
        guardado.setEmail("sergiesote@gmail.com");
        //Guardo los cambios
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById(100).orElse(null);

        Assertions.assertEquals("sergiesote@gmail.com", usuarioBuscado.getEmail());

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();
        //Assertions.assertEquals(3, usuarios.size());
        //System.out.println(usuarios);
        usuarios.forEach(u -> System.out.println(u));
    }

}
