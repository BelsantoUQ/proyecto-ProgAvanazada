package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
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
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){

        Usuario usuario = usuarioRepo.findById(100).orElse(null);

        Compra compra = new Compra("TARJETADECREDITO", usuario);

        Compra guardado1 = compraRepo.save(compra);

        Assertions.assertNotNull(guardado1);

    }


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:compras.sql")
    public void eliminarTest(){

        compraRepo.deleteById(1);

        Compra buscado = compraRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:compras.sql")
    public void actualizarTest(){

        Compra guardado = compraRepo.findById(2).orElse(null);
        //Modifico
        guardado.setMedioPago("EFECTIVO");
        //Guardo los cambios
        compraRepo.save(guardado);

        Compra buscado = compraRepo.findById(2).orElse(null);

        Assertions.assertEquals("EFECTIVO", buscado.getMedioPago());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:compras.sql")
    public void listarTest(){

        List<Compra> compras = compraRepo.findAll();

        compras.forEach(u -> System.out.println(u));
    }


}
