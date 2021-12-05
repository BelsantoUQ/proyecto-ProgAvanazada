package co.edu.uniquindio.proyecto.interfaceService;


import co.edu.uniquindio.proyecto.entidades.DetalleCompra;

import java.util.List;

public interface IDetalleCompraService {

    DetalleCompra guardar (DetalleCompra dc) throws Exception;

    void eliminar(int codigo) throws Exception;

    DetalleCompra obtener(int codigo) throws Exception;

    List<DetalleCompra> listar();
}
