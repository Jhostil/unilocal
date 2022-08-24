package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    @Autowired
    private ModeradorRepo moderadorRepo;

    public void eliminarModerador(String email){

        moderadorRepo.deleteById(email);
    }

    @Override
    public Moderador registrarModerador(Moderador m) throws Exception {

        Moderador buscado = moderadorRepo.findByEmail(m.getEmail());

        Moderador ModeradorNick = moderadorRepo.findByNickname(m.getNickname());


        if (buscado != null) {
            throw new Exception("El correo electronico ya se encuentra en uso");
        }
        if (ModeradorNick != null) {
            throw new Exception("El NickName del moderador ya se encuentra registrado");
        }
        if (ModeradorNick != null) {
            throw new Exception("El NickName del moderador ya se encuentra registrado");
        }
        if (m.getNickname().length() > 100) {
            throw new Exception("El NickName debe contener menos de 100 caracteres");
        }
        if (m.getEmail().length() > 200) {
            throw new Exception("El email debe contener menos de 200 caracteres");
        }
        if (m.getNombre().length() > 200) {
            throw new Exception("El nombre debe contener menos de 200 caracteres");
        }
        if (m.getContrasena().length() > 200) {
            throw new Exception("La contraseña debe contener menos de 200 caracteres");
        } else {
            System.out.println(m.getEmail() + "" + m.getEmail());
        }

        return moderadorRepo.save(m);
    }

}
