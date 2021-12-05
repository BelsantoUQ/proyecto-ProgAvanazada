package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
@ViewScoped
public class ProductoPuntosBean implements Serializable {


    @Autowired
    private IProductoService productoServicio;

    @Getter
    @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){

        this.productos = listarTodo();
    }

    private List<Producto> listarTodo() {
        return productoServicio.listarProductosPuntos();
    }


}