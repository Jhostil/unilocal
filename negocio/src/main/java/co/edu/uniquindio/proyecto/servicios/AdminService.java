package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

public interface AdminService {

    Administrador registrarAdmin(Administrador admin) throws Exception;

    boolean existenAdmins() throws Exception;
}
