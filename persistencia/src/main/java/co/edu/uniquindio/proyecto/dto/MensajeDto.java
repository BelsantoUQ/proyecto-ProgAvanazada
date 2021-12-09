package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MensajeDto {
    int codigo, codigoChat;
    LocalDateTime fecha;
    String mensaje, userEmisor;

    public MensajeDto(String mensaje, String emisor){
        this.mensaje = mensaje;
        this.userEmisor = emisor;
    }
}
