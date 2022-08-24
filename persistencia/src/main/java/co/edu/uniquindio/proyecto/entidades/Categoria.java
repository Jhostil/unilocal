package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Categoria implements Serializable {


    //atributos de la calse Categoria
    //llave primaria id
    @Id
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Getter
    @Setter
    @Size(max = 200, message = "El tamaño maximo debe ser de 200 caracteres")
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    @Getter
    @Setter
    @NotBlank(message = "El campo Nombre es obligatorio")
    @Size(max = 50, message = "El tamaño maximo debe ser de 50 caracteres")
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;


    //relacion uno a uno categoria y lugar(entidad propietaria)
    @ToString.Exclude
    @Getter
    @Setter
    @OneToMany(mappedBy = "categoria")
    private List<Lugar> lugar;

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}