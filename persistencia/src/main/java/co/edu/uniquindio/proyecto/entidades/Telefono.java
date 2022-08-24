package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

//Entidad Telefono del Lugar
@Entity
public class Telefono implements Serializable {
    //Atributos pertenecientes a la Entidad Telefono
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "numero", unique = true)
    @Positive
    @NotBlank(message = "El campo Numero es obligatorio")
    @Size(max = 10, message = "El tamaño maximo de Numero es de 10 caracteres")
    private String numero;
    //Relacion de Telefono con Lugar de muchos a uno
    @Getter
    @Setter
    @ManyToOne
    private Lugar lugar;

    //Constructor Telefono sin parametros

    public Telefono() {
        super();
    }

    //Sobre carga de Constructor Telefono con parametro numero de tipo String
    public Telefono(@Positive String numero) {

        this.numero = numero;
    }

    //metodos Set y Get de los atributos de Telefono
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    //Metodo equals and hashCode para corroborar la llave primaria codigo de la entidad Telefono
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telefono telefono = (Telefono) o;

        return codigo == telefono.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
