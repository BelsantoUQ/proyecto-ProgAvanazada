package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {


    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MensajeRepo mensajeRepo;

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    public void registrarTest(){

        Chat chat = chatRepo.findById(1).orElse(null);

        Mensaje mensaje = new Mensaje("Buenas tardes", "tu", chat);
        Mensaje guardado1 = mensajeRepo.save(mensaje);

        Assertions.assertNotNull(guardado1);

    }


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    @Sql("classpath:mensajes.sql")
    public void eliminarTest(){

        mensajeRepo.deleteById(1);

        Mensaje buscado = mensajeRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    @Sql("classpath:mensajes.sql")
    public void actualizarTest(){

        Mensaje guardado = mensajeRepo.findById(2).orElse(null);
        //Modifico
        guardado.setMensaje("Hoola buenas tardes");
        //Guardo los cambios
        mensajeRepo.save(guardado);

        Mensaje buscado = mensajeRepo.findById(2).orElse(null);

        Assertions.assertEquals("Hoola buenas tardes", buscado.getMensaje());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    @Sql("classpath:mensajes.sql")
    public void listarTest(){

        List<Mensaje> mensajes = mensajeRepo.findAll();

        mensajes.forEach(u -> System.out.println(u));
    }


}
