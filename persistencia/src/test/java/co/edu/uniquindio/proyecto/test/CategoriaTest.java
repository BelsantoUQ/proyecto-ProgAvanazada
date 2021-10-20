package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registrarTest(){
        Categoria categoria = new Categoria("Aseo Personal");

        Categoria categoriaGuardada = categoriaRepo.save(categoria);

        Assertions.assertNotNull(categoriaGuardada);

    }
    @Test
    @Sql("classpath:categorias.sql")
    public void eliminarTest(){

        categoriaRepo.deleteById(2);

        Categoria categoriaBuscada = categoriaRepo.findById(2).orElse(null);

        Assertions.assertNull(categoriaBuscada);
    }

    @Test
    @Sql("classpath:categorias.sql")
    public void actualizarTest(){

        Categoria guardado = categoriaRepo.findById(1).orElse(null);

        //Modifico el nombre
        guardado.setNombre("Pines o Codigos");
        //Guardo los cambios
        categoriaRepo.save(guardado);

        Categoria categoriaBuscada = categoriaRepo.findById(1).orElse(null);

        Assertions.assertEquals("Pines o Codigos", categoriaBuscada.getNombre());

    }

    @Test
    @Sql("classpath:categorias.sql")
    public void listarTest(){

        List<Categoria> categorias = categoriaRepo.findAll();
        categorias.forEach(u -> System.out.println(u));
    }



}
