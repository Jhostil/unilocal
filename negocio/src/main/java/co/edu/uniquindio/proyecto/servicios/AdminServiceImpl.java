package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    private final AdministradorRepo administradorRepo;

    public AdminServiceImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Administrador registrarAdmin(Administrador admin) throws Exception {
        return administradorRepo.save(admin);
    }

    @Override
    public boolean existenAdmins() throws Exception {
        boolean resultado = true;
        List<Administrador> admin = administradorRepo.findAll();
        if (admin == null || admin.size() == 0) {
            resultado = false;
        }
        return resultado;
    }
}
