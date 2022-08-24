package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarketDTO;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {


    private String busqueda;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    private List<Lugar> lugares;

    @Autowired
    private LugarServicio lugarServicio;


    @PostConstruct
    public void inicializar() throws Exception {
        if (busquedaParam != null && !busquedaParam.isEmpty()) {


            lugares = lugarServicio.buscarPorNombre(busquedaParam);

            PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(this.lugares.stream().map(l -> new MarketDTO(l.getId(), l.getNombre(), l.getDescripcion(), l.getLatitud(), l.getLongitud())).collect(Collectors.toList())) + ");");

        }
    }

    public String buscar() {

         /*if(existe==false)
         {
             return "/";
         }*/
        if (!busqueda.isEmpty()) {
            System.out.println("entroo");
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }

        return "";
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getBusquedaParam() {
        return busquedaParam;
    }

    public void setBusquedaParam(String busquedaParam) {
        this.busquedaParam = busquedaParam;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public LugarServicio getLugarServicio() {
        return lugarServicio;
    }

    public void setLugarServicio(LugarServicio lugarServicio) {
        this.lugarServicio = lugarServicio;
    }
}
