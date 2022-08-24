package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Resena;
import co.edu.uniquindio.proyecto.entidades.Telefono;
import co.edu.uniquindio.proyecto.repositorios.ResenaRepo;
import co.edu.uniquindio.proyecto.repositorios.TelefonoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase ResenaTest para las pruebas CRUD de la entidad Resena
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ResenaTest {

    @Autowired
    private ResenaRepo resenaRepo;

    //Metodo para probar El registro de Resenas en la base de datos
    @Test
    public void registrarResenaTest() {

        Resena resenaNuevo = new Resena(1, "Es el mejor lugar al que he ido");
        Resena resenaGuardado = resenaRepo.save(resenaNuevo);

        Assertions.assertNotNull(resenaGuardado);
    }

    //Metodo para probar la eliminacion de Registros de las Resenas existentes en la base de datos
    @Test
    public void eliminarResenaTest() {

        Resena resenaNuevo = new Resena(1, "Es el mejor lugar al que he ido");
        Resena resenaGuardado = resenaRepo.save(resenaNuevo);
        int llave = resenaGuardado.getCodigo();

        resenaRepo.delete(resenaNuevo);

        Resena resenaBorrado = resenaRepo.findById(llave).orElse(null);

        Assertions.assertNull(resenaBorrado);
    }

    //Metodo para probar el cambio de informacion de un dato existente perteneciente a una Resena hecha por un Usuario existente en la base de datos
    @Test
    public void actualizarResenaTest() {

        Resena resenaNuevo = new Resena(1, "Es el mejor lugar al que he ido");
        Resena resenaGuardado = resenaRepo.save(resenaNuevo);


        resenaGuardado.setCalificacion(1);
        resenaRepo.save(resenaGuardado);

        int llave = resenaGuardado.getCodigo();

        Resena resenaBuscado = resenaRepo.findById(llave).orElse(null);

        Assertions.assertEquals(1, resenaBuscado.getCalificacion());
    }

    //Metodo para probar el enlistado de todos los Registros de Resenas existentes en la base de datos
    @Test
    @Sql("classpath:resena.sql")
    public void listarResenaTest() {
        List<Resena> lista = resenaRepo.findAll();
        System.out.println(lista);
    }
}
