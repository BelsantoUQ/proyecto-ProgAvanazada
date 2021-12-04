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
public class InicioBean implements Serializable {


    @Autowired
    private IProductoService productoServicio;

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Categoria>categoriasSelect;

    @Getter @Setter
    private List<Categoria> categorias;

    @PostConstruct
    public void inicializar(){

        this.productos = listarTodo();
        this.categorias = productoServicio.listarCategorias();
    }

    private List<Producto> listarTodo() {
        return productoServicio.listar();
    }

    public String irADetalle(String id){
        return "detalleProducto?faces-redirect=true&amp;producto="+id;
    }

    public void actualizarLista(){

        if(categoriasSelect!=null && !categoriasSelect.isEmpty()){
            List<Producto> newListP = new ArrayList<>();
            for (int i = 0; i < categoriasSelect.size(); i++) {
                System.out.println(categoriasSelect.size()+"---- aqui ---- "+categoriasSelect.get(i));
                newListP.addAll(productoServicio.listarValidosPorCategoria(categoriasSelect.get(i)));
            }
            categoriasSelect.clear();
            productos.clear();
            Producto p;
            for (int i = 0; i < newListP.size(); i++) {
                p=newListP.get(i);
                if (!productos.contains(p)){
                    productos.add(p);
                }
            }
        }else{
            productos = listarTodo();
        }
    }
}
