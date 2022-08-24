package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface LugarServicio {

    List<Lugar> buscarPorNombre(String nombre) throws Exception;

    List<Lugar> buscarPorCategoria(String categoria) throws Exception;

    List<Lugar> buscarPorCategoriaYNombre(String nombre) throws Exception;

    Lugar buscarPorNombreExacto(String nombre) throws Exception;

    Lugar crearLugar(Lugar l) throws Exception;

    //RegistroLugar AutorizarLugares(RegistroLugar r, int estado) throws Exception;


    void eliminarLugar(Integer id) throws Exception;

    Lugar actualizarLugar(Lugar lugar) throws Exception;

    List<Lugar> listarLugares();

    List<Categoria> listarCategorias();

    Lugar obtenerLugar(Integer id) throws Exception;

    Categoria crearCategoria(Categoria categoria) throws Exception;

    Categoria obtenerCategoria(Integer id) throws Exception;

    List<Resena> listarResenas(Integer idLugar);

    List<Horario> listarHorario(Integer idLugar);

    Telefono crearTelefono(Telefono telefono);

    Lugar RegistrarLugar(Lugar r) throws Exception;


    void crearResena(Resena resena) throws Exception;

    Imagen crearImagen(Imagen i) throws Exception;
}
