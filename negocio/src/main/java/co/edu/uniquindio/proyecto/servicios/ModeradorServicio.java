package co.edu.uniquindio.proyecto.servicios;

import ch.qos.logback.core.encoder.EchoEncoder;
import co.edu.uniquindio.proyecto.entidades.Moderador;

public interface ModeradorServicio {

    Moderador registrarModerador(Moderador m) throws Exception;

    void  eliminarModerador(String email) throws Exception;

}
