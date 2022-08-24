package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repositorio categoria con llave integer
@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
