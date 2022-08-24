package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//Entidad Horario por dias
@Entity
public class Horario implements Serializable {

    //Atributos pertenecientes a la Entidad Horario las horas cumplen un formato de "00:00 am" 0 "00:00 pm"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "hora_apertura", length = 8, nullable = false)
    private String horaApertura;
    @Column(name = "hora_cierre", length = 8, nullable = false)
    private String horaCierre;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "dia", nullable = false)
    private Dia dia;

    //Relacion con lugar de muchos a uno
    @Getter
    @Setter
    @ManyToOne
    private Lugar lugar;

    //Constructor Horario sin parametros
    public Horario() {
        super();
    }

    //Sobre carga de Constructor Horario con parametros de hora de apertura, hora de cierre y dia
    public Horario(String horaApertura, String horaCierre, Dia dia) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.dia = dia;
    }

    //metodos Set y Get de los atributos de Horario
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horCierre) {
        this.horaCierre = horaCierre;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    //Metodo equals and hashCode para corroborar la llave primaria codigo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        return codigo == horario.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

}
