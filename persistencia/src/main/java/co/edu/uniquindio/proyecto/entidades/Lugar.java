package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.*;

@ToString
@Entity
public class Lugar implements Serializable {

    //atributos de la clase lugar
    //llave primaria id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;
    @Column(name = "descripcion", length = 500)
    @Size(max = 500, message = "El tamaño máximo es de 500 caracteres")
    private String descripcion;
    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El campo Nombre debe ser obligatorio")
    @Size(max = 100, message = "El tamaño máximo es de 100 caracteres")
    private String nombre;
    @Column(name = "direccion", length = 100, nullable = false)
    @NotBlank(message = "El campo Dirección debe ser obligatorio")
    @Size(max = 100)
    private String direccion;
    @Column(name = "latitud", precision = 3, scale = 6, nullable = false)
    @DecimalMax(value = "90", message = "El rango maximo de la Latitud es hasta 90.0")
    @DecimalMin(value = "-90", message = "El rango minimo de la Latitud es hasta -90.0")
    private double latitud;
    @Column(name = "longitud", precision = 2, scale = 6, nullable = false)
    @DecimalMax(value = "180", message = "El rango maximo de la Longitud es hasta -180.0")
    @DecimalMin(value = "-180", message = "El rango minimo de la Longitud es hasta -180.0")
    private double longitud;

    //relaciones entre entidades
    //relacion uno a muchos entre lugar y horario(entidad propietaria)
    @Getter
    @Setter
    @OneToMany(mappedBy = "lugar")
    private List<Horario> horario;
    //relacion uno a uno entre lugar y registrolugar(entidad propietaria)

    //relacion uno a muchos entre lugar y telefono(entidad propietaria)
    @Getter
    @OneToMany(mappedBy = "lugar")
    private List<Telefono> telefono;
    //relacion uno a uno entre lugar y categoria
    @Getter
    @Setter
    @ManyToOne
    private Categoria categoria;
    //relacion muchos a uno entre lugar y ciudad
    @Getter
    @Setter
    @ManyToOne
    private Ciudad ciudad;
    //relacion muchos a uno entre lugar y usuario
    @Getter
    @Setter
    @ManyToOne
    private Usuario usuario;
    @Column(name = "seguidores", nullable = false)
    @Getter
    @Setter
    @ManyToMany
    private List<Usuario> usuarios;
    //relacion uno a muchos entre lugar y resena(entidad propietaria)
    @Getter
    @Setter
    @OneToMany(mappedBy = "lugar")
    private List<Resena> resena;
    //relacion uno a muchos entre lugar y imagen(entidad propietaria)
    @Getter
    @Setter
    @OneToMany(mappedBy = "lugar")
    private List<Imagen> imagen;


    //get y set
    public Lugar() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }


    //metodos de comparacion de la llave primaria
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lugar lugar = (Lugar) o;
        return id == lugar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //metodo constructor de lugar
    public Lugar(String descripcion, String nombre, String direccion, double latitud, double longitud) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Builder
    public Lugar(String descripcion, String nombre, String direccion, double latitud, double longitud, Usuario usuarioCreador,
                 ArrayList<Imagen> imagenes, Categoria categoria, ArrayList<Horario> horarios, Ciudad ciudad) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.usuario = usuarioCreador;
        this.imagen = imagenes;
        this.categoria = categoria;
        this.horario = horarios;
        this.ciudad = ciudad;
    }

    public String getImagenPrincipal() {
        if (imagen != null && !imagen.isEmpty()) {
            return imagen.get(0).getLink();
        }
        return "default.jpg";
    }

    public List<Resena> getResena() {
        return resena;
    }

    public void setResena(List<Resena> resena) {
        this.resena = resena;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono.add(telefono);
    }

   // public void setRegistrarLugar(Lugar registroLugar) {
    //}
    //Llave principal de la entidad que en este caso es un código autogenerado de tipo entero

    /*
        Atributo de la entidad que es de tipo enum. Este atributo nos indica el estado de el lugar,
         si está aprobado, rechazado o en espera de aprobación
         El atributo no puede ser null
         */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado")
    private Estado estado;
    /*
    Atributo de la entidad que es de tipo date. Este atributo nos indica la fecha de creación de la solicitud del registro del lugar
    El atributo no puede ser null
     */
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    /*
    Atributo de la entidad que es de tipo date. Este atributo nos indica la fecha de aprobación de la solicitud del registro del lugar
    El atributo no puede ser null
     */
    @Column(name = "fecha_aprobacion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;

    //Relación con la entidad Moderador. Nos indica que un moderador tiene muchas solicitudes de registroLugar
    @Getter
    @Setter
    @ManyToOne
    private Moderador moderador;

    //Constructor vacío de la entidad

    /*
    Métodos get y set de la entidad. Los set nos sirve para asignarle valores a los atributos.
    Los get sirven para obtener los valores de los atributos
     */



    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public void RegistrarLugar(Lugar l) {
    }
}