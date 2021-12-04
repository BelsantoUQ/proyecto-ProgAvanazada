package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;


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
        if(busquedaParam!=null && !busquedaParam.isEmpty()){
            productos = listarTodo();
        }

        this.productos = listarTodo();
        this.categorias = productoServicio.listarCategorias();
    }

    public String buscar(){
        return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }

    public void actualizarLista(){

        if(categoriasSelect!=null && !categoriasSelect.isEmpty()){
            List<Producto> newListP = new ArrayList<>();
            for (int i = 0; i < categoriasSelect.size(); i++) {
                System.out.println(categoriasSelect.size()+"---- aqui ---- "+categoriasSelect.get(i));
                newListP.addAll(productoServicio.buscarIncluirCategoria(busquedaParam,categoriasSelect.get(i)));
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

    private List<Producto> listarTodo() {
        return productoServicio.buscarProductos(busquedaParam);
    }


}
