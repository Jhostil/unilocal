package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MarketDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private double lat, lng;
}
