package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.interfaceService.ICompraService;
import co.edu.uniquindio.proyecto.interfaceService.ISubastaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ViewScoped
@Component
public class ComprasBean {


    @Autowired
    private ICompraService compraServicio;

    @Getter
    @Setter
    private List<Compra> compras;

    @Getter
    @Setter
    private Usuario usuarioC;

    @Getter
    @Setter
    private List<DetalleCompra> detalleCompra;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        compras = listarTodo();
    }

    public List<Compra> listarTodo() {
        try {
            return compraServicio.obtenerPorUsuario(usuarioSesion);
        } catch (Exception e) {
            e.printStackTrace();
        }return new ArrayList<>();
    }

    public List<DetalleCompra> obtenerDetalleCompra(Compra compra){
        try {
            return compraServicio.obtenerDetalleCompra(compra);
        } catch (Exception e) {
            e.printStackTrace();
        }return null;

    }

    public String toStringDetalleCompra(Compra c){
        String contenido = "Productos ";
        List<DetalleCompra> detallesCompra = obtenerDetalleCompra(c);
        Producto p;
        int unidades;
        double precio;
        double subtotal = 0;
        for(DetalleCompra dc:detallesCompra){
            p=dc.getProductoCompra();
            unidades=dc.getUnidades();
            precio = dc.getPrecioProducto();
            subtotal=+precio*unidades;
            contenido+= p.getNombre()+" x"+unidades+" - "+precio+"c/u. ";
        }
        contenido+=" <br/> Valor Total:"+subtotal;

        return contenido;
    }

}