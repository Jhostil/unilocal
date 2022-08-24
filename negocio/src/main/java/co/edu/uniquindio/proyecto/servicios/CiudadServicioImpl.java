package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImpl implements CiudadServicio {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) {

        return ciudadRepo.save(ciudad);

    }

    @Override
    public Ciudad obtenerCiudad(Integer id) throws Exception {

        Optional<Ciudad> objeto = ciudadRepo.findById(id);

        if (objeto.isEmpty()) {
            throw new Exception("El id no es válido");
        }
        return objeto.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

}
