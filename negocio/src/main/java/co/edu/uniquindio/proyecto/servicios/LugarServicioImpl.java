package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LugarServicioImpl implements LugarServicio {

    private final LugarRepo lugarRepo;
    private final CategoriaRepo categoriaRepo;
    private final ResenaRepo resenaRepo;
    private final TelefonoRepo telefonoRepo;
    private final ImagenRepo imagenRepo;

    public LugarServicioImpl(LugarRepo lugarRepo, CategoriaRepo categoriaRepo, ResenaRepo resenaRepo, TelefonoRepo telefonoRepo, ImagenRepo imagenRepo) {
        this.lugarRepo = lugarRepo;
        this.categoriaRepo = categoriaRepo;
        this.resenaRepo = resenaRepo;
        this.telefonoRepo = telefonoRepo;
        this.imagenRepo = imagenRepo;

    }


    //
    @Override
    public List<Lugar> buscarPorNombre(String nombre) throws Exception {
        List<Lugar> buscado = lugarRepo.buscarPorCampo(nombre);
        if (buscado == null || buscado.size() == 0) {
            throw new Exception("No se encuentra ningun lugar con ese nombre");
        }

        return buscado;
    }

    @Override
    public List<Lugar> buscarPorCategoria(String nombre) throws Exception {
        List<Lugar> buscado = lugarRepo.buscarPorCateoria(nombre);
        if (buscado == null || buscado.size() == 0) {
            throw new Exception("No se encuentra ningun lugar con esa categoria");
        }
        return buscado;
    }


    @Override
    public List<Lugar> buscarPorCategoriaYNombre(String nombre) throws Exception {
        List<Lugar> buscado = lugarRepo.buscarPorCateoria(nombre);
        List<Lugar> buscadoNombre1 = lugarRepo.buscarPorCateoria(nombre);

        buscado.addAll(buscadoNombre1);

        if (buscado == null || buscado.size() == 0) {
            List<Lugar> buscadoCategoria = lugarRepo.buscarPorCateoria(nombre);
            List<Lugar> buscadoNombre = lugarRepo.buscarPorCampo(nombre);

            if ((buscadoCategoria == null || buscadoCategoria.size() == 0) && (buscadoNombre == null || buscadoNombre.size() == 0)) {
                throw new Exception("No se encuentra ningun lugar con ese nombre ni categoria");
            } else if (buscadoCategoria == null || buscadoCategoria.size() == 0) {
                throw new Exception("No se encuentra ningun lugar con esa categoria");
            } else {
                throw new Exception("No se encuentra ningun lugar con ese nombre");

            }

        }

        return buscado;
    }

    //

    public Lugar buscarPorNombreExacto(String nombre) throws Exception {
        Lugar lugar = lugarRepo.findByNombre(nombre);

        if (lugar == null) {
            throw new Exception("Lugar no encontrado");
        }
        return lugar;
    }

    @Override
    public Lugar crearLugar(Lugar l) throws Exception {

        Date ahora = new Date();

        l.setFechaCreacion(ahora);
        l.setEstado(Estado.EN_ESPERA);


        if (l.getDescripcion() == null) {
            l.setDescripcion("");
        }
        if (l.getDireccion() == null || l.getDireccion().trim().equals("")) {
            throw new Exception("El campo de direccion está vacio ");
        }
        if (l.getNombre() == null || l.getNombre().trim().equals("")) {
            throw new Exception("El campo del nombre está vacio ");
        }

        if (l.getDescripcion().length() > 500) {

            throw new Exception("La descripción debe contener menos de 500 caracteres");
        }
        if (l.getDireccion().length() > 100) {

            throw new Exception("La dirección debe contener menos de 100 caracteres");
        }
        if (l.getNombre().length() > 100) {

            throw new Exception("El nombre debe contener menos de 100 caracteres");
        }
        if (l.getDescripcion().length() > 500) {

            throw new Exception("La descripción debe contener menos de 500 caracteres");
        }
        if (l.getLatitud() > 90 || l.getLatitud() < -90) {

            throw new Exception("El rango de la latitud debe ser de -90 a 90");
        }
        if (l.getLongitud() > 180 || l.getLongitud() < -180) {

            throw new Exception("El rango de la longitud debe ser de -180 a 180");
        }


        return lugarRepo.save(l);

    }


    @Override
    public void eliminarLugar(Integer id) throws Exception {

    }

    @Override
    public Lugar actualizarLugar(Lugar lugar) throws Exception {
        return null;
    }

    @Override
    public List<Lugar> listarLugares() {
        return lugarRepo.findAll();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Lugar obtenerLugar(Integer id) throws Exception {
        Optional<Lugar> objeto = lugarRepo.findById(id);

        if (objeto.isEmpty()) {
            throw new Exception("El id no es valido");
        }
        return objeto.get();
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) throws Exception {
        return categoriaRepo.save(categoria);
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception {
        Optional<Categoria> objeto = categoriaRepo.findById(id);
        if (objeto.isEmpty()) {
            throw new Exception("El Id no es valido");
        }
        return objeto.get();
    }

    @Override
    public List<Resena> listarResenas(Integer idLugar) {


        return lugarRepo.listarResenas(idLugar);
    }

    @Override
    public List<Horario> listarHorario(Integer idLugar) {
        return lugarRepo.listarHorarios(idLugar);
    }

    @Override
    public Telefono crearTelefono(Telefono telefono) {

        return telefonoRepo.save(telefono);
    }

    @Override
    public Lugar RegistrarLugar(Lugar r) throws Exception {
        return null;
    }


    @Override
    public void crearResena(Resena resena) throws Exception {
        try {
            resena.setFechaCreacion(new Date());
            resenaRepo.save(resena);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Imagen crearImagen(Imagen i) throws Exception {
        return imagenRepo.save(i);
    }

    /*public RegistroLugar AutorizarLugares(RegistroLugar r, int estado) {
        if (estado ==2) {
            r.setEstado(Estado.APROBADO);
            Date ahora = new Date();
            r.setFechaAprobacion(ahora);
            System.out.println("el contexto tiene un precio" + ahora);
            PrintStream var10000 = System.out;
            LocalDate var10001 = LocalDate.now();
            var10000.println(var10001 + " fechisimo" + r.getFechaAprobacion());
        }

        if (estado==1) {
            r.setEstado(Estado.RECHAZADO);
        }

        return r;
    }*/
}
