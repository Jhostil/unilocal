package co.edu.uniquindio.proyecto.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//Entidad Resena del lugar incluye un comentario y una calificacion
@ToString
@Entity
public class Resena implements Serializable {
    //Atributos pertenecientes a la Entidad Resena
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "calificacion", nullable = false)
    @Positive
    @Max(value = 5, message = "La Calificacion debe ser menor o igual a 5")
    private int calificacion;
    @Column(name = "comentario", length = 500)
    @Size(max = 500, message = "El Comentario debe ser menor a 500 caracteres")
    private String comentario;

    @ToString.Exclude
    @Column(name = "respuesta", length = 500, nullable = true)
    @Size(max = 500, message = "La Respuesta deber ser menor a 500 caracteres")
    private String respuesta;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaCreacion;
    //Relacion de Resena con Lugar de muchos a uno
    @ToString.Exclude
    @Getter
    @Setter
    @ManyToOne
    private Lugar lugar;
    //Relacion de Resena con Usuario de muchos a uno
    @ToString.Exclude
    @Getter
    @Setter
    @ManyToOne
    private Usuario usuario;

    public Resena() {
        super();
    }

    @Builder
    //Sobre carga de Constructor Resena con parametros Calificacion de tipo Entero y comentario Tipo String limitado a 500 caracteres
    public Resena(int calificacion, String comentario, Lugar lugar, Usuario usuario) {
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.lugar = lugar;
        this.usuario = usuario;
    }

    public Resena(int calificacion, String comentario) {
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    //metodos Set y Get de los atributos de Resena
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    //Metodo equals and hashCode para corroborar la llave primaria codigo de la entidad Resena
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resena resena = (Resena) o;

        return codigo == resena.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
