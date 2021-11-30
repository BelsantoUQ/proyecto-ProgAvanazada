package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {

    @Query("select dc.compra, sum(dc.unidades*dc.precioProducto) from DetalleCompra dc where dc.compra.comprador.codigo = :codigo group by dc.compra")
    List<Object[]> obtenerTotalComprasUsuario(int codigo);

    @Query("select dc.productoCompra, sum(dc.unidades) as suma from DetalleCompra dc where :categoria member of dc.productoCompra.categorias group by dc.productoCompra order by suma desc")
    Optional<Producto> obtenerMasVendidoPorCategoria(Categoria categoria);

}
