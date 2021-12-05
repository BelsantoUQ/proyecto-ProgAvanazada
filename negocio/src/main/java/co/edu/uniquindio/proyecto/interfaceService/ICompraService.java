package co.edu.uniquindio.proyecto.interfaceService;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface ICompraService {

    Compra guardar (Compra c) throws Exception;

    void eliminar(int codigo) throws Exception;

    Compra obtener(int codigo) throws Exception;

    List<Compra> obtenerPorUsuario(Usuario u);

    List<DetalleCompra> obtenerDetalleCompra(Compra c) throws Exception;

    List<Compra> listar();

}
