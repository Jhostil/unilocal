package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;
    private Usuario usuario;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter
    @Setter
    private Ciudad ciudad;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void inicializar() {

        this.usuario = new Usuario();
        this.ciudades = ciudadServicio.listarCiudades();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage("mensajep", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajep", msg);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
