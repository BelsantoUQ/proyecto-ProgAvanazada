package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.interfaceService.IDetalleCompraService;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraService implements IDetalleCompraService {

    @Autowired
    private DetalleCompraRepo data;


    @Override
    public DetalleCompra guardar(DetalleCompra dc) throws Exception {

        if (data.findById(dc.getCodigo()).isEmpty()){
            return data.save(dc);
        }else throw new Exception("No se puede guardar ya hay un detalle compra con este codigo");
    }

    @Override
    public void eliminar(int codigo) throws Exception {

        if (data.findById(codigo).isEmpty()){
            data.deleteById(codigo);
        }else throw new Exception("No se puedo encontrar un detalle de compra con este codigo");
    }

    @Override
    public DetalleCompra obtener(int codigo) throws Exception {

        return data.findById(codigo).orElseThrow(() -> new Exception("No se puede guardar ya hay un detalle de compra con este codigo"));
    }

    @Override
    public List<DetalleCompra> listar() {
        return data.findAll();
    }
}
