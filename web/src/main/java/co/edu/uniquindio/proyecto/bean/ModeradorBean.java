package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component
@RequestScope
public class ModeradorBean {

    @Autowired
    private ModeradorServicio moderadorServicio;
    @Getter @Setter
    private Moderador moderador;

    @Getter @Setter
    private Administrador administrador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @PostConstruct
    public void inicializar() {

        this.moderador = new Moderador();
        //this.administrador = new Administrador();
    }
    public void registrarModerador() {
        try {
            moderador.setAdministrador((Administrador) personaLogin);
            moderadorServicio.registrarModerador(moderador);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage("mensajep", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajep", msg);
        }
    }
    public void eliminarModerador(){
        try {
            moderadorServicio.eliminarModerador(moderador.getEmail());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage("mensajep", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajep", msg);        }
    }

}
