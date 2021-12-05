package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer>{

    @Query("select c.medioPago, count(c) from Compra c group by c.medioPago")
    List<Object[]> listarCantidadByMedioPago();

    List<Compra> findAllByComprador(Usuario u);

    @Query("select dc from DetalleCompra dc where dc.compra.codigo= :codigoCompra")
    List<DetalleCompra> obtenerDetalles(int codigoCompra);


}
