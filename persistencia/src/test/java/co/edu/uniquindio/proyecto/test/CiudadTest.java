package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo cityRepo;

    @Test
    public void registroTest(){

        Ciudad ciudad = new Ciudad("OHIO");


        Ciudad ciudadGuardada1 = cityRepo.save(ciudad);

        Assertions.assertNotNull(ciudadGuardada1);

    }


    @Test
    @Sql("classpath:ciudades.sql")
    public void eliminarTest(){

        cityRepo.deleteById(1001);

        Ciudad usuarioBuscado = cityRepo.findById(1001).orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    public void actualizarTest(){

        Ciudad guardado = cityRepo.findById(1002).orElse(null);

        //Modifico
        guardado.setNombre("Texas");
        //Guardo los cambios
        cityRepo.save(guardado);

        Ciudad ciudadBuscada = cityRepo.findById(1002).orElse(null);

        Assertions.assertEquals("Texas", ciudadBuscada.getNombre());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    public void listarTest(){

        List<Ciudad> ciudades = cityRepo.findAll();
        ciudades.forEach(u -> System.out.println(u));
    }



}
