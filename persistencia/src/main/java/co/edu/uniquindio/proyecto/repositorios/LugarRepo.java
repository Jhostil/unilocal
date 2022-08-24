package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//repositorio de lugar con llave integer
@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    Lugar findByNombre(String nombre);

    @Query("select l from Lugar l where l.nombre like %:nombre%")
    List<Lugar> buscarPorCampo(@Param("nombre") String nombre);

    @Query("select l from Lugar l where l.categoria.nombre=:nombre ")
    List<Lugar> buscarPorCateoria(@Param("nombre") String nombre);


   // @Query("select l from Lugar l where l.categoria.codigo=:categoriaId and l.nombre like %:nombre% ")
   // List<Lugar> buscarPorCateoriaYNombre(@Param("nombre") String nombre, @Param("categoriaId") int categoriaId);

    @Query("select r from Resena r where r.lugar.id=:idLugar")
    List<Resena> listarResenas(Integer idLugar);

    @Query("select h from Horario h where h.lugar.id=:idLugar")
    List<Horario> listarHorarios(Integer idLugar);

}
