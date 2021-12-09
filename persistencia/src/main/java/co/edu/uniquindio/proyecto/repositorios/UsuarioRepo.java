package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {

   // @Query("select u from Usuario u where u.nombre = :nombre")
   // List<Usuario>obtenerUsuarioPorNombre(String nombre);

    List<Usuario> findAllByNombreContains(String nombre);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmailAndPassword(String username, String password);

    @Modifying
    @Query(value="DELETE FROM `usuario_productos_favoritos` WHERE usuarios_potenciales_codigo = :u AND productos_favoritos_codigo = :p", nativeQuery = true)
    void eliminarFavorito(int u, String p);

    Page<Usuario> findAll(Pageable paginador);

    @Query("select p from Usuario u, IN (u.productosFavoritos) p where u.email = :email")
    List<Producto> obtenerProductosFavoritos(String email);

// para mostrar todos  @Query("select u.email, u.nombre, p from Usuario u left join u.productosVenta p")
    @Query("select u.email, u.nombre, p from Usuario u join u.productosVenta p")
    List<Object[]>listarUsuariosYProductos();


}
