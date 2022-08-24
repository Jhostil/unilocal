package co.edu.uniquindio.proyecto.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//Entidad Administrador el cual heredará de Persona sus atributos y metodos
@Entity
public class Administrador extends Persona implements Serializable {

    //Relacion de Administrador y Moderadores de uno a muchos
    @Getter
    @Setter
    @OneToMany(mappedBy = "administrador")
    private List<Moderador> moderadores;

    //Relacion de Administrador y reportes de uno a muchos
    @Getter
    @Setter
    @OneToMany(mappedBy = "administrador")
    private List<Reporte> reportes;

    //contructor sin parametros
    public Administrador() {
        super();
    }

    //constructor sobrecargado
    public Administrador(String email, String nombre, String nickname, String contrasena, double latitud, double longitud) {
        super(email, nombre, nickname, contrasena, latitud, longitud);
    }
}
