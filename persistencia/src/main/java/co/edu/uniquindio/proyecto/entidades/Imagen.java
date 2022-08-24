package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Imagen {

    //Llave principal de entidad, en este caso es un codigo autogenerado de tipo entero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    //atributo que sirve para guardar la ruta de la imagen
    @Column(name = "link", length = 1000, nullable = false)
    private String link;

    //Relación con la entidad Lugar que nos indica que la entidad Lugar tiene muchas Imagenes.
    @Getter
    @Setter
    @ManyToOne
    private Lugar lugar;

    /*
    Métodos get y set de la entidad. Los set nos sirven para asignar valores a los atributos. Los get sirven para obtener el valor de los atributos
     */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagen imagen = (Imagen) o;
        return codigo == imagen.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    //Constructor vacío de la entidad
    public Imagen() {
        super();
    }

    //Constructor sobrecargado que recibe por parámetro el link, que viene siendo la ruta donde se almacena la imagen
    public Imagen(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "codigo=" + codigo +
                ", link='" + link + '\'' +
                '}';
    }
}
