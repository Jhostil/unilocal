package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import org.springframework.stereotype.Service;


public interface PersonaServicio {

    Persona login(String email, String contrasena) throws  Exception;
}
