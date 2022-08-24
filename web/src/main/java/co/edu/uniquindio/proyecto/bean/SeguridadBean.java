package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.PersonaServicio;
import com.sun.faces.el.ELConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    @NotBlank
    private String email, contrasena;

    @Autowired
    private PersonaServicio personaServicio;

    @Getter @Setter
    private String rol;


    public String iniciarSesion(){
        if(email!=null&&contrasena!=null) {
            try {
                persona=personaServicio.login(email, contrasena);
                if(persona instanceof Usuario) {
                    rol="usuario";

                }
                else if(persona instanceof Administrador){
                    rol = "admin";
                }
                else{
                    rol="moderador";
                }
                autenticado=true;
                return "/index?faces-redirect=true" ;
            } catch (Exception e) {
                FacesMessage m= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",m);

            }
        }
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
}
