package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Interfaz que sirve para el depósito de datos, extiende de JpaRepository
 le indicamos de que tipo de entidad a la que referencia el repositorio que en este caso es Imagen
 y seguido le indicamos el tipo de dato de la llave primaria que en este caso es de tipo entera
 */
@Repository
public interface ImagenRepo extends JpaRepository<Imagen, Integer> {
}
