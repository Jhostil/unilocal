package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarketDTO;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class InicioBean implements Serializable {


    @Autowired
    private LugarServicio lugarServicio;

    @Getter
    @Setter
    private List<Lugar> lugares;

    @PostConstruct
    public void inicializar() {

        this.lugares = lugarServicio.listarLugares();
        PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(this.lugares.stream().map(l -> new MarketDTO(l.getId(), l.getNombre(), l.getDescripcion(), l.getLatitud(), l.getLongitud())).collect(Collectors.toList())) + ");");

    }

    public String irADetalle(Integer id) {
        return "/detalleLugar?faces-redirect=true&amp;lugar=" + id;
    }
}
