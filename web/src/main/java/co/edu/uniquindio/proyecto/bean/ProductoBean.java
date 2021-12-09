package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.interfaceService.ICiudadService;
import co.edu.uniquindio.proyecto.interfaceService.IProductoService;
import co.edu.uniquindio.proyecto.entidades.Producto;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private IProductoService productoService;

    @Getter @Setter
    private ArrayList<String> imagenes;

    @Getter @Setter
    private ArrayList<Categoria> categoriasSelect;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private ICiudadService ciudadServicio;

    @Value("${upload.url}")
    private String urlUpload;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializat(){
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        this.categoriasSelect = new ArrayList<>();
        this.ciudades = ciudadServicio.listar();
        this.categorias = productoService.listarCategorias();
    }

    public String crearProducto(){
        if (!imagenes.isEmpty() && usuarioSesion!=null){
            try {

                Usuario vendedor = usuarioSesion;
                String cat = generarCodigoP();
                producto.setCodigo(vendedor.getCodigo()+cat+productoService.listar().size());
                producto.setVendedor(vendedor);
                producto.setFechaLimite(LocalDate.now().plusMonths(1));
                productoService.guardar(producto);
                productoService.actualizarCategorias(categoriasSelect, producto.getCodigo());
                productoService.actualizarImagen(imagenes, producto.getCodigo());
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Agregado con exito");
                FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
                return "detalleProducto?faces-redirect=true&amp;producto="+producto.getCodigo();
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
                return "";
            }

        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Error no se han subido alguna imagen del producto");
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
            return "";
        }
    }

    private String generarCodigoP() {
        String city = producto.getCiudadProducto().getNombre();
        String cod = ""+city.charAt(0);
        for (int i = 0; i < this.categoriasSelect.size(); i++) {
            cod+=""+producto.getCategorias().get(i).toString().toUpperCase(Locale.ROOT).charAt(0)
                    +producto.getCategorias().get(i).toString().toUpperCase(Locale.ROOT).charAt(1);
        }
        return cod;
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
        }
    }

    private String subirImagen(UploadedFile imagen) {
        try {
            File archivo = new File(urlUpload + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagen.getFileName();
    }


}
