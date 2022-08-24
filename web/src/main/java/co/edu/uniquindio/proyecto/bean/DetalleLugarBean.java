package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetalleLugarBean implements Serializable {

    @Getter @Setter
    private Float calificacionPromedio;

    @Value("#{param['lugar']}")
    private String idLugar;

    @Getter @Setter
    private Resena nuevoComenatrio;

    @Autowired
    private LugarServicio lugarServicio;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Resena> resenas;

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private String icono;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;



    @PostConstruct
    public void init() {
        this.nuevoComenatrio = new Resena();
        this.icono = "pi pi-star-o";


        if (idLugar != null && !"".equals(idLugar)) {
            try {
                int id = Integer.parseInt(idLugar);
                this.lugar = lugarServicio.obtenerLugar(id);
                this.resenas = lugarServicio.listarResenas(id);
                this.horarios = lugarServicio.listarHorario(id);
                //this.calificacionPromedio = lugarServicio.obtenerCalificacionPromedio(id)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void crearComentario(){

        try {
            if (personaLogin != null)
            {
                nuevoComenatrio.setLugar(this.lugar);
                nuevoComenatrio.setUsuario((Usuario) personaLogin);
                lugarServicio.crearResena(nuevoComenatrio);
                this.nuevoComenatrio = new Resena();
            }

          } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void marcarFavorito(){
        if (icono.equals("pi pi-star-o")){
            this.icono= "pi pi-star";
        }
        else{
            this.icono="pi pi-star-o";
        }
        if(personaLogin != null)
        {
           // lugarServicio.marcarFavorito(this.lugar,(Usuario) personaLogin); //sesion
        }


    }

}
