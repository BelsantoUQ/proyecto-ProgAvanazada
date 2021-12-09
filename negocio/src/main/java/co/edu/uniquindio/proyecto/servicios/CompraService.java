package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ICompraService;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private CompraRepo data;

    @Override
    public Compra guardar(Compra c) throws Exception {

        if (data.findById(c.getCodigo()).isEmpty()){
            return data.save(c);
        }else throw new Exception("No se puede guardar ya hay una compra con este codigo");
    }

    @Override
    public void eliminar(int codigo) throws Exception {

        if (data.findById(codigo).isEmpty()){
             data.deleteById(codigo);
        }else throw new Exception("No se puedo encontrar una compra con este codigo");
    }

    @Override
    public List<Compra> obtenerPorUsuario(Usuario u){

        return data.findAllByComprador(u);
    }

    @Override
    public List<DetalleCompra> obtenerDetalleCompra(Compra compra) throws Exception {
        if (!data.findById(compra.getCodigo()).isEmpty()) {
            return data.obtenerDetalles(compra.getCodigo());
        }else throw new Exception("No se encontro la compra");
    }

    @Override
    public Compra obtener(int codigo) throws Exception {

        return data.findById(codigo).orElseThrow(() -> new Exception("No se puede guardar ya hay una compra con este codigo"));
    }

    @Override
    public List<Compra> listar() {
        return data.findAll();
    }

    @Override
    public List<Compra> listarPorMes(LocalDateTime f1, LocalDateTime f2) {
        return data.obtenerComprarMes(f1, f2);
    }
}
