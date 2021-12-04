package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private String codigo, nombre, imagen;
    private int unidades, unidadesMax, valor_en_puntos;
    private float precio, descuento;

    public String mostrarDescuento(){
        int dcto= (int) (descuento*100);
        return "%"+dcto;
    }

    public float getPrecioTotalConDcto(){
        return this.getPrecioConDcto()*unidades;
    }
    public float getPrecioConDcto(){
        return (precio-(precio*descuento));
    }

}
