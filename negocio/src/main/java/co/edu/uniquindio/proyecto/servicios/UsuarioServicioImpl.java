package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {


    private final UsuarioRepo usuarioRepo;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;

    }


    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        System.out.println("holaHD");
        Usuario buscado = usuarioRepo.findByEmail(u.getEmail());
        Usuario UsuarioNick = usuarioRepo.findByNickname(u.getNickname());
        if (buscado != null) {
            throw new Exception("El correo electronico ya se encuentra en uso");
        }
        if (UsuarioNick != null) {
            throw new Exception("El NickName del usuario ya se encuentra registrado");
        }
        if (UsuarioNick != null) {
            throw new Exception("El NickName del usuario ya se encuentra registrado");
        }
        if (u.getNickname().length() > 100) {
            throw new Exception("El NickName debe contener menos de 100 caracteres");
        }
        if (u.getEmail().length() > 200) {
            throw new Exception("El email debe contener menos de 200 caracteres");
        }
        if (u.getNombre().length() > 200) {
            throw new Exception("El nombre debe contener menos de 200 caracteres");
        }
        if (u.getContrasena().length() > 200) {
            throw new Exception("La contraseña debe contener menos de 200 caracteres");
        } else {
            System.out.println(u.getEmail() + "" + u.getEmail());
        }

        return usuarioRepo.save(u);
    }

    /*
    @Author: Jhoiner Lopez
     */
    @Override
    public Usuario buscarUsuario(String email) throws Exception {

        Usuario usuario = usuarioRepo.findByEmail(email);

        if (usuario == null) {
            throw new Exception("Usuario no existe");
        }

        return usuario;
    }


}
