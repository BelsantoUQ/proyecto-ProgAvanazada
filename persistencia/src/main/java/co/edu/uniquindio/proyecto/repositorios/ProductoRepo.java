package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, String> {

    List<Producto> findAllByVendedorUsername(String username);

    List<Producto> findAllByNombreContains(String nombre);

    @Query("select p.comentarios from Producto p where p.codigo = :codigo")
    List<Comentario> listarComentarios(String codigo);

    @Query("select p.vendedor.nombre from Producto p where p.codigo = :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select p.vendedor from Producto p where p.vendedor.username = :username")
    Optional<Usuario> obtenerVendedorPorUsername(String username);

    @Query("select p.vendedor from Producto p where p.vendedor.codigo = :codigo")
    Optional<Usuario> obtenerVendedorPorCodigo(int codigo);

    @Query("select  p, c from Producto p join p.comentarios c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.userComent from Producto p join p.comentarios c where p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(String id);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre, p.descripcion, p.precio, p.ciudadProducto) from Producto p where current_timestamp < p.fechaLimite and p.unidades > 0")
    List<ProductoValido> listarProductosValidos();

    @Query("select p from Producto p where current_timestamp < p.fechaLimite and p.unidades > 0")
    List<Producto> listarValidos();

    @Query("select p from Producto p where :categoria member of p.categorias and current_timestamp < p.fechaLimite and p.unidades > 0")
    List<Producto> listarValidosPorCategoria(Categoria categoria);

    @Query("select p from Producto p, IN(p.vendedor) u where u.username = :username")
    List<Producto> obtenerProductosVendedor(String username);

    @Query("select avg(c.calificacion) from Producto p join p.comentarios c where p.codigo = :codigo")
    float obtenerPromedioCalificaciones(String codigo);

    @Query("select c, count (s) from Subasta s join s.productoEnSubasta.categorias c where s.fechaLimite > current_timestamp group by c")
    float obtenerCantidadProductosSubastaC();

    @Query("select cat, avg(c.calificacion) as prom from Comentario c join c.productoC.categorias cat group by cat order by prom desc")
    List<Categoria> listarCategoriasPromedioCalificacion();


}
