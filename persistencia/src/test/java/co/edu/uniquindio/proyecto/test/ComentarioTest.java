package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void registrarTest(){

        Usuario usuario = usuarioRepo.findById(101).orElse(null);
        Producto producto = productoRepo.findById("1E").orElse(null);

        Comentario comentario = new Comentario("No me gusto aparte huele feo", "Puede ser por la transportadora", 2, usuario, producto);

        Comentario comentarioGuardado = comentarioRepo.save(comentario);
        Assertions.assertNotNull(comentarioGuardado);

    }
    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:comentarios.sql")
    public void eliminarTest(){

        comentarioRepo.deleteById(1);

        Comentario buscado = comentarioRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:comentarios.sql")
    public void actualizarTest(){

        Comentario guardado = comentarioRepo.findById(2).orElse(null);
        Usuario usuario = usuarioRepo.findById(101).orElse(null);
        //Modifico el usuario
        guardado.setUserComent(usuario);
        //Guardo los cambios
        comentarioRepo.save(guardado);

        Comentario comentBuscado = comentarioRepo.findById(2).orElse(null);

        Assertions.assertEquals(usuario, comentBuscado.getUserComent());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:comentarios.sql")
    public void listarTest(){

        List<Comentario> comentarios = comentarioRepo.findAll();

        comentarios.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:comentarios.sql")
    public void listarConRango(){
        List<Comentario> comentarios = comentarioRepo.listarComentariosRango1(1,4);
        List<Comentario> comentarios2 = comentarioRepo.listarComentariosRango1(3,5);
        System.out.println(comentarios2);
        System.out.println(comentarios);
    }

}
