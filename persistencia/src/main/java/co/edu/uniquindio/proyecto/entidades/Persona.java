package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

// Clase padre de la cual heredaran Usuario, Administrador, Moderador,
// usando la estrategia de tablas por clase la cual mapeará todos los atributos
// y metodos a los hijos
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Persona implements Serializable {

    @Id
    @Column(name = "email", length = 200)

    @Size(max = 200, message = "El tamaño maximo es de 200 caracteres")
    @NotBlank(message = "El Email es obligatorio")
    @Email(message = "El correo es invalido")
    private String email;
    @Column(name = "nombre", length = 200, nullable = false)
    @Size(max = 200, message = "El tamaño maximo es de 200 caracteres")
    @NotBlank(message = "El campo Nombre es obligatorio")
    private String nombre;
    @Column(name = "nickname", length = 100, nullable = false, unique = true)
    @Size(max = 100, message = "El tamaño maximo es de 100 caracteres")
    @NotBlank(message = "El campo NickName es obligatoria")
    private String nickname;
    @Column(name = "contrasena", length = 200, nullable = false)
    @Size(max = 200, message = "El tamaño maximo es de 200 caracteres")
    @NotBlank(message = "El campo Contraseña es obligatorio")
    private String contrasena;
    @Column(name = "latitud", precision = 3, scale = 6)
    @DecimalMax(value = "90", message = "El rango maximo de la Latitud es hasta 90.0")
    @DecimalMin(value = "-90", message = "El rango minimo de la Latitud es hasta -90.0")
    private double latitud;
    @Column(name = "longitud", precision = 2, scale = 6)
    @DecimalMax(value = "180", message = "El rango maximo de la Longitud es hasta -180.0")
    @DecimalMin(value = "-180", message = "El rango minimo de la Longitud es hasta -180.0")
    private double longitud;

    //contructor sin parametros
    public Persona() {
        super();
    }

    //constructor sobrecargado
    public Persona(String email, String nombre, String nickname, String contrasena, double latitud, double longitud) {
        this.email = email;
        this.nombre = nombre;
        this.nickname = nickname;
        this.contrasena = contrasena;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters y Setters de los atributos
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return email != null ? email.equals(persona.email) : persona.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
