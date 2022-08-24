package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    public CategoriaServicioImpl(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception {

        Optional<Categoria> objeto = categoriaRepo.findById(id);
        if (objeto.isEmpty()) {
            throw new Exception("El id no es válido");
        }
        return objeto.get();
    }
}
