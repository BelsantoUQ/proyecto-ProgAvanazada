package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import com.zaxxer.hikari.util.FastList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {


    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ChatRepo chatRepo;


    @Autowired
    private CiudadRepo cityRepo;


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){

        Ciudad ciudad = cityRepo.findById(1001).orElse(null);

        Categoria categoria1 = Categoria.GAMING;
        Categoria categoria2 = Categoria.SMARTPHONE;
        List<Categoria>categorias = Arrays.asList(categoria1,categoria2);



        Usuario usuario = usuarioRepo.findById(100).orElse(null);

        Producto producto = new Producto("8TR","Microfono MAX3", 80,
                "Tiene bloqueo de sonido y es compatible con mac", 0, 260000, LocalDate.now(), 0,categorias,usuario,ciudad);


        Producto guardado1 = productoRepo.save(producto);

        Assertions.assertNotNull(guardado1);

    }



    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void eliminarTest(){

        productoRepo.deleteById("1E");

        Producto buscado = productoRepo.findById("1E").orElse(null);

        Assertions.assertNull(buscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void actualizarTest(){

        Producto guardado = productoRepo.findById("1E").orElse(null);
        //Modifico
        guardado.setDescripcion("Compatible con xbox one, series s y series x");
        //Guardo los cambios
        productoRepo.save(guardado);

        Producto buscado = productoRepo.findById("1E").orElse(null);

        Assertions.assertEquals("Compatible con xbox one, series s y series x", buscado.getDescripcion());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void listarTest(){

        List<Producto> productos = productoRepo.findAll();

        productos.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void obtenerNombreVendedorTest(){
        String nombre = productoRepo.obtenerNombreVendedor(102);
        Assertions.assertEquals("Sergio Esteban Latorre",nombre);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void listarProductosYComentariosTest(){
        List<Object[]> respuesta = productoRepo.listarProductosYComentarios();

        respuesta.forEach(o -> System.out.println(o[0]
                +"...."+o[1]+"...."+o[2]));

    }


    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    @Sql("classpath:comentarios.sql")
    public void listarUsuariosComentariosTest(){

        List<Usuario> usuarios = productoRepo.listarUsuariosComentarios("1E");
        usuarios.forEach(System.out::println);
        Assertions.assertEquals(1, usuarios.size());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void listarProductosValidosTest(){

        List<ProductoValido> productos = productoRepo.listarProductosValidos();
        productos.forEach(System.out::println);
        Assertions.assertEquals(2, productos.size());
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:categorias.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void listarProductosValidosPorCategoriaTest(){

        List<ProductoValido> productos = productoRepo.listarProductosValidos();
        productos.forEach(System.out::println);
        Assertions.assertEquals(2, productos.size());
    }
}
