package co.edu.uniquindio.proyecto.entidades;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class Ciudad {

    //Lave primaria de la entidad Ciudad, es un número autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    //Atributo que sirve para asignar un nombre cuando se crea una Ciudad
    @Column(name = "nombre", length = 60, nullable = false)
    @NotBlank(message = "El campo Nombre es obligatorio")
    @Size(max = 60, message = "El tamaño máximo es de 60 caracteres")
    private String nombre;
    @Getter
    @Setter
    //Relación con la entidad Lugar, donde indica que una Ciudad tiene una lista de Lugares
    @OneToMany(mappedBy = "ciudad")
    private List<Lugar> lugar;
    @Getter
    @Setter
    //Relación con la entidad Usuario, donde indica que la Ciudad tiene un lista de Ususarios
    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    //Constructor vacío de la Entidad
    public Ciudad() {
        super();
    }

    /*
    Metodos get y set de la entidad. Los set sirven para asignarle valores a los atributos; los get sirven para
    obtener los valores de los atributos
     */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return codigo == ciudad.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    //Constructor sobrecargado de la entidad que recibe por parámetro el atributo nombre
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}