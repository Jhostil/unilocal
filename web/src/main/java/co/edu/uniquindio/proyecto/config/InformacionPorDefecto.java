package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.AdminService;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

     @Override
    public void run(String... args) throws Exception {


         if (!adminService.existenAdmins()) {
             //Administrador ad1 = Administrador.builder().nombre("Admin 1").email("admin1@admin.com").nickname("admin1").password("123").build();
             Administrador ad1 = new Administrador("admin1@admin.com", "Admin1", "admin1", "123", 0, 0);
             adminService.registrarAdmin(ad1);

             //Administrador ad2 = Administrador.builder().nombre("Admin 2").email("admin2@admin.com").nickname("admin2").password("123").build();
             Administrador ad2 = new Administrador("admin2@admin.com", "Admin2", "admin2", "123", 0, 0);
             adminService.registrarAdmin(ad2);
         }

         if (ciudadServicio.listarCiudades().isEmpty()) {
             Ciudad C1 = new Ciudad("Armenia");
             Ciudad C2 = new Ciudad("Medellin");
             Ciudad C3 = new Ciudad("Pereira");
             Ciudad C4 = new Ciudad("Cali");

             ciudadServicio.registrarCiudad(C1);
             ciudadServicio.registrarCiudad(C2);
             ciudadServicio.registrarCiudad(C3);
             ciudadServicio.registrarCiudad(C4);
         }

         if (lugarServicio.listarCategorias().isEmpty()) {
             Categoria ca1 = new Categoria("Restaurante", "Lugar para comer");
             Categoria ca2 = new Categoria("Hotel", "Lugar para hospedarser");
             Categoria ca3 = new Categoria("Café", "Lugar para beber café");
             Categoria ca4 = new Categoria("Bar", "Lugar para beber");

             lugarServicio.crearCategoria(ca1);
             lugarServicio.crearCategoria(ca2);
             lugarServicio.crearCategoria(ca3);
             lugarServicio.crearCategoria(ca4);

         }
        /* usuarioServicio.registrarUsuario(u1);
         Lugar l1 = Lugar.builder().nombre("Coffe break")
                 .ciudad(ciudadServicio.obtenerCiudad(1))
                 .descripcion("Café, café y más café")
                 .direccion("Calle 5 con 5")
                 .latitud(67)
                 .longitud(34)
                 .usuarioCreador(usuarioServicio.buscarUsuario("mario@gmail.com"))
                 .imagenes(new ArrayList<>())
                 .categoria(lugarServicio.obtenerCategoria(1))
                 .horarios(new ArrayList<>()).build();

         Lugar l2 = Lugar.builder().nombre("hotel el tiempo")
                 .ciudad(ciudadServicio.obtenerCiudad(1))
                 .descripcion("hotel sin techo")
                 .direccion("Calle ojo me roban")
                 .latitud(78)
                 .longitud(34)
                 .usuarioCreador(usuarioServicio.buscarUsuario("mario@gmail.com"))
                 .imagenes(new ArrayList<>())
                 .categoria(lugarServicio.obtenerCategoria(1))
                 .horarios(new ArrayList<>()).build();

         lugarServicio.crearLugar(l1);
         lugarServicio.crearLugar(l2);

         Resena r1 = Resena.builder()
                .comentario("Está más o menos")
                .calificacion(3)
                .usuario(usuarioServicio.buscarUsuario("mario@gmail.com"))
                .lugar(lugarServicio.obtenerLugar(1)).build();

        lugarServicio.crearResena(r1);

        Resena r2 = Resena.builder()
                .comentario("Está lo maximo (no soy bot)")
                .calificacion(3)
                .usuario(usuarioServicio.buscarUsuario("mario@gmail.com"))
                .lugar(lugarServicio.obtenerLugar(2)).build();

        lugarServicio.crearResena(r2);
*/
     }

}
