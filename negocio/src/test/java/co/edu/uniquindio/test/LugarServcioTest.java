package co.edu.uniquindio.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Estado;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class LugarServcioTest {

    @Autowired
    private LugarServicio lugarServicio;

    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugaresPorNombreTest() throws Exception {

        List<Lugar> lista = lugarServicio.buscarPorNombre("maria");
        for (Lugar l : lista)
            System.out.println(l.getNombre());
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugaresPorCatagoriaTest() throws Exception {

        List<Lugar> lista = lugarServicio.buscarPorCategoria("Hotel");

        for (Lugar l : lista) {
            System.out.println(l.getNombre());

        }


    }


    @Test
    @Sql("classpath:lugares.sql")
    public void buscarPorCategoriaYNombreaTest() throws Exception {

        List<Lugar> lista = lugarServicio.buscarPorCategoriaYNombre("maria");

        for (Lugar l : lista) {
            System.out.println(l.getNombre());

        }


    }

    @Test
    public void crearLugar() throws Exception {
        Lugar lugarNuevo = new Lugar("restaurante de mariscos", "mariamar", "carrera 20", 20.177776, 34.000564);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaCreacion = sdf.parse("1996/02/14");
        Date fechaAprobacion = sdf.parse("2021/01/16");

        //RegistroLugar registroLugarNuevo = new RegistroLugar(Estado.APROBADO, fechaCreacion, fechaAprobacion);

        Lugar lugarGuardado = lugarServicio.crearLugar(lugarNuevo);

        System.out.println(lugarGuardado.getNombre());
        Assertions.assertNotNull(lugarGuardado);

    }


}
