package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.repositorios.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonaServicioImpl implements PersonaServicio {

    @Autowired
    private PersonaRepo personaRepo;


    @Override
    public Persona login(String email, String contrasena) throws Exception {
        Optional<Persona> persona=personaRepo.findByEmailAndContrasena(email,contrasena);

        if(persona.isEmpty()){

            throw new Exception("Los datos de autenticacion son incorrectos");
        }
        return persona.get();
    }
}
