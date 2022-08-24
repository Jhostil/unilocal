package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario buscarUsuario(String email) throws Exception;


}
