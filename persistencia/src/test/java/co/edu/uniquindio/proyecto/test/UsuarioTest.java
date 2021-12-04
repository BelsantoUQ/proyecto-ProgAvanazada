package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:ciudades.sql")
    public void registrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1001).orElse(null);

        List< String> telefonos = new ArrayList<>();
        telefonos.add("3165411222");
        telefonos.add("3175419888");
        Usuario usuario = new Usuario("Carlos Esteban","userwow","carlito@gmail.com","12345",telefonos, ciudad, 0);


        Usuario usuarioGuardado1 = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado1);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById(101);

        Usuario usuarioBuscado = usuarioRepo.findById(101).orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void actualizarTest(){

        Usuario guardado = usuarioRepo.findById(100).orElse(null);

        //Modifico
        guardado.setEmail("sergiesote@gmail.com");
        //Guardo los cambios
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById(100).orElse(null);

        Assertions.assertEquals("sergiesote@gmail.com", usuarioBuscado.getEmail());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void listarTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();
        //Assertions.assertEquals(3, usuarios.size());
        //System.out.println(usuarios);
        usuarios.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void filtrarNombreTest(){
        List<Usuario> listaUsuarios = usuarioRepo.findAllByNombreContains("Sergio");
        listaUsuarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void paginarListaTest() {
        Pageable paginator = PageRequest.of(0, 2);
        Page<Usuario> lista = usuarioRepo.findAll(paginator);
        System.out.println(lista.stream().collect(Collectors.toList()));
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    public void odenarListaTest() {

        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void productosFavoritosTest() {

        List<Producto> favoritos = usuarioRepo.obtenerProductosFavoritos("elemailaleta@gmail.com");
        Assertions.assertEquals(2, favoritos.size());
    }

    @Test
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:productos.sql")
    public void listarUsuariosProductosTest(){
        List<Object[]>respuesta = usuarioRepo.listarUsuariosYProductos();

        for(Object[] objeto: respuesta){
            System.out.println(objeto[0]+"...."+objeto[1]+"...."+objeto[2]);
        }
    }
}
