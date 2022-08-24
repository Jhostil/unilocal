package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import javassist.bytecode.ExceptionsAttribute;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad ciudad) throws Exception;

    Ciudad obtenerCiudad(Integer id) throws Exception;

    List<Ciudad> listarCiudades();
}
