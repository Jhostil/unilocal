package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Resena;
import co.edu.uniquindio.proyecto.repositorios.ResenaRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

@Service
public class ResenaServicioImpl implements ResenaServicio {

    private ResenaRepo resenaRepo;


    public ResenaServicioImpl(ResenaRepo resenaRepo) {
        this.resenaRepo = resenaRepo;
    }

    /*
    @Author: Jhoiner Lopez
     */
    @Override
    public Resena registrarComentario(Resena r) throws Exception {

        if (r.getComentario().length() > 500) {

            throw new Exception("El comentario no debe tener más de 500 caracteres");
        }

        if (r.getRespuesta() != null) {
            if (r.getRespuesta().length() > 500) {

                throw new Exception("La respuesta no debe tener mas de 500 caracteres");
            }
        }


        return resenaRepo.save(r);
    }


}
