package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer>{

    @Query("select c.medioPago, count(c) from Compra c group by c.medioPago")
    List<Object[]> listarCantidadByMedioPago();

    List<Compra> findAllByComprador(Usuario u);

    @Query("select dc from DetalleCompra dc where dc.compra.codigo= :codigoCompra")
    List<DetalleCompra> obtenerDetalles(int codigoCompra);

    @Query("select c from Compra c where c.fechaCompra between :fecha1 and :fecha2")
    List<Compra> obtenerComprarMes(LocalDateTime fecha1, LocalDateTime fecha2);


}
