package co.edu.uniquindio.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Resena;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.ResenaServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ResenaServicioTest {


    @Autowired
    private ResenaServicio resenaServicio;
    @Autowired
    private LugarServicio lugarServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;


    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:resenas.sql")
    @Sql("classpath:usuarios.sql")
    public void registrarResena() throws Exception {

        Resena resena = new Resena(3, "Buen lugar");
        Resena resena2 = new Resena(1, "Mal lugar");
        Resena resena3 = new Resena(2, "Regular");
        Lugar lugar = lugarServicio.buscarPorNombreExacto("maria");

        Usuario usuario = usuarioServicio.buscarUsuario("laura@outlook.es");
        resena.setLugar(lugar);
        resena.setUsuario(usuario);

        usuario = usuarioServicio.buscarUsuario("juan123@gmail.com");
        resena2.setLugar(lugar);
        resena2.setUsuario(usuario);

        usuario = usuarioServicio.buscarUsuario("fer123@gmail.com");
        resena3.setLugar(lugar);
        resena3.setUsuario(usuario);

        resenaServicio.registrarComentario(resena);
        resenaServicio.registrarComentario(resena2);
        resenaServicio.registrarComentario(resena3);


        Lugar lugar1 = lugarServicio.buscarPorNombreExacto("maria");

        List<Resena> r1 = lugar1.getResena();

        for (Resena r : r1) {

            System.out.println("Comentario: " + r.getComentario() + "  " + "usuario: " + r.getUsuario().getNombre());

        }
        Assertions.assertNotNull(r1);

    }

}
