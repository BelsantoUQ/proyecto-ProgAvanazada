package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
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
public class AdministradorTest {

    @Autowired
    private AdministradorRepo adminRepo;

    @Test
    public void registrarTest(){

        Administrador admin = new Administrador("Carlos Esteban","soyunico@gmail.com","12345");

        Administrador usuarioGuardado1 = adminRepo.save(admin);

        Assertions.assertNotNull(usuarioGuardado1);
    }

    @Test
    @Sql("classpath:administradores.sql")
    public void eliminarTest(){

        adminRepo.deleteById(11);

        Administrador adminBuscado = adminRepo.findById(11).orElse(null);

        Assertions.assertNull(adminBuscado);
    }


    @Test
    @Sql("classpath:administradores.sql")
    public void actualizarTest(){

        Administrador guardado = adminRepo.findById(10).orElse(null);

        //Modifico el email de usuario
        guardado.setEmail("prueba@gmail.com");
        //Guardo los cambios
        adminRepo.save(guardado);

        Administrador adminBuscado = adminRepo.findById(10).orElse(null);

        Assertions.assertEquals("prueba@gmail.com", adminBuscado.getEmail());

    }

    @Test
    @Sql("classpath:administradores.sql")
    public void listarTest(){

        List<Administrador> admins = adminRepo.findAll();
        admins.forEach(u -> System.out.println(u));
    }

}
