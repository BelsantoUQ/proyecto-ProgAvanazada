package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.Subasta_UsuarioRepo;
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
public class SubastaUsuarioTest {


    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private Subasta_UsuarioRepo subastaUsuarioRepo;


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    public void registrarTest(){



        Usuario usuario = usuarioRepo.findById(100).orElse(null);
        Subasta subasta = subastaRepo.findById(1).orElse(null);

        Subasta_Usuario subasta_usuario = new Subasta_Usuario(80000,usuario,subasta);


        Subasta_Usuario chatGuardado1 = subastaUsuarioRepo.save(subasta_usuario);

        Assertions.assertNotNull(chatGuardado1);

    }



    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    @Sql("classpath:subastas_usuarios.sql")
    public void eliminarTest(){

        subastaUsuarioRepo.deleteById(1);

        Subasta_Usuario buscado = subastaUsuarioRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    @Sql("classpath:subastas_usuarios.sql")
    public void actualizarTest(){

        Subasta_Usuario guardado = subastaUsuarioRepo.findById(2).orElse(null);
        //Modifico
        guardado.setValor(100000);
        //Guardo los cambios
        subastaUsuarioRepo.save(guardado);

        Subasta_Usuario buscado = subastaUsuarioRepo.findById(2).orElse(null);

        Assertions.assertEquals(100000, buscado.getValor());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    @Sql("classpath:subastas_usuarios.sql")
    public void listarTest(){

        List<Subasta_Usuario> subastas = subastaUsuarioRepo.findAll();

        subastas.forEach(u -> System.out.println(u));
    }


}
