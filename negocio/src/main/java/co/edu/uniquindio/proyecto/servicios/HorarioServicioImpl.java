package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.repositorios.HorarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioServicioImpl implements HorarioServicio {


    private final HorarioRepo horarioRepo;

    public HorarioServicioImpl(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }


    @Override
    public Horario CrearHorario() {
        return null;
    }
}
