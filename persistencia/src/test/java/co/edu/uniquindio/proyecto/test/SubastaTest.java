package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {


    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void registrarTest(){

        Producto producto = productoRepo.findById("1E").orElse(null);

        Subasta subasta = new Subasta(LocalDateTime.of(2021,10,28, 2,00,00), producto);


        Subasta guardado1 = subastaRepo.save(subasta);

        Assertions.assertNotNull(guardado1);

    }


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    public void eliminarTest(){

        subastaRepo.deleteById(1);

        Subasta buscado = subastaRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    public void actualizarTest(){

        Subasta guardado = subastaRepo.findById(2).orElse(null);
        //Modifico el usuario
        guardado.setFechaLimite(LocalDateTime.of(2021,10,28, 2,00,00));
        //Guardo los cambios
        subastaRepo.save(guardado);

        Subasta buscado = subastaRepo.findById(2).orElse(null);

        Assertions.assertEquals(LocalDateTime.of(2021,10,28, 2,00,00), buscado.getFechaLimite());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:subastas.sql")
    public void listarTest(){

        List<Subasta> subastas = subastaRepo.findAll();

        subastas.forEach(u -> System.out.println(u));
    }

}
