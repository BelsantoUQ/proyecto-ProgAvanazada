package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class detalleCompraTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:compras.sql")
    public void registrarTest(){


        Producto producto = productoRepo.findById("1E").orElse(null);
        Compra compra = compraRepo.findById(1).orElse(null);

        DetalleCompra detalleCompra = new DetalleCompra(10, 80000, producto, compra);


        DetalleCompra guardado1 = detalleCompraRepo.save(detalleCompra);

        Assertions.assertNotNull(guardado1);

    }


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:compras.sql")
    @Sql("classpath:detallescompras.sql")
    public void eliminarTest(){

        detalleCompraRepo.deleteById(1);

        DetalleCompra buscado = detalleCompraRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:compras.sql")
    @Sql("classpath:detallescompras.sql")
    public void actualizarTest(){

        DetalleCompra guardado = detalleCompraRepo.findById(2).orElse(null);
        //Modifico el usuario
        guardado.setUnidades(4);
        //Guardo los cambios
        detalleCompraRepo.save(guardado);

        DetalleCompra buscado = detalleCompraRepo.findById(2).orElse(null);

        Assertions.assertEquals(4, buscado.getUnidades());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:compras.sql")
    @Sql("classpath:detallescompras.sql")
    public void listarTest(){

        List<DetalleCompra> details = detalleCompraRepo.findAll();

        details.forEach(u -> System.out.println(u));
    }


}
