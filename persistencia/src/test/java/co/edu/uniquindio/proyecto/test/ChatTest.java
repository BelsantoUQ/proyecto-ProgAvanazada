package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
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
public class ChatTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ChatRepo chatRepo;


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void registrarTest(){


        Producto producto = productoRepo.findById("1E").orElse(null);

        Usuario usuario = usuarioRepo.findById(100).orElse(null);

        Chat chat = new Chat(usuario, producto);


        Chat chatGuardado1 = chatRepo.save(chat);

        Assertions.assertNotNull(chatGuardado1);

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    public void eliminarTest(){

        chatRepo.deleteById(1);

        Chat chatBuscado = chatRepo.findById(1).orElse(null);

        Assertions.assertNull(chatBuscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    public void actualizarTest(){

        Chat guardado = chatRepo.findById(2).orElse(null);
        Usuario usuario = usuarioRepo.findById(101).orElse(null);
        //Modifico el usuario
        guardado.setChatUsuario(usuario);
        //Guardo los cambios
        chatRepo.save(guardado);

        Chat chatBuscado = chatRepo.findById(2).orElse(null);

        Assertions.assertEquals(usuario, chatBuscado.getChatUsuario());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:chats.sql")
    public void listarTest(){

        List<Chat> chats = chatRepo.findAll();

        chats.forEach(u -> System.out.println(u));
    }

}
