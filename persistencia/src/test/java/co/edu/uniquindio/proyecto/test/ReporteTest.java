package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase ReporteTest para las pruebas CRUD de la entidad Reporte
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReporteTest {

    @Autowired
    private ReporteRepo reporteRepo;

    //Metodo para simular el registro del reporte en la base de datos
    @Test
    public void registrarReporteTest() {

        Reporte reporteNuevo = new Reporte("Cantidad masculinos:45, Cantidad femeninos:65");
        Reporte reporteGuardado = reporteRepo.save(reporteNuevo);

        Assertions.assertNotNull(reporteGuardado);
    }

    //Metodo para simular la eliminacion de un reporte en la base de datos
    @Test
    public void eliminarReporteTest() {

        Reporte reporteNuevo = new Reporte("Cantidad masculinos:45, Cantidad femeninos:65");
        reporteRepo.save(reporteNuevo);

        reporteRepo.delete(reporteNuevo);

        Reporte reporteBorrado = reporteRepo.findById(reporteNuevo.getCodigo()).orElse(null);

        Assertions.assertNull(reporteBorrado);
    }

    //Metodo para simular el cambio de informacion de un dato existente perteneciente a un reporte en la base de datos
    @Test
    public void actualizarReporteTest() {

        Reporte reporteNuevo = new Reporte("Cantidad masculinos:45, Cantidad femeninos:65");
        Reporte reporteGuardado = reporteRepo.save(reporteNuevo);

        reporteGuardado.setDescripcion("Cantidad masculinos:60, Cantidad femeninos:85");
        reporteRepo.save(reporteGuardado);

        Reporte reporteBuscado = reporteRepo.findById(reporteNuevo.getCodigo()).orElse(null);

        Assertions.assertEquals("Cantidad masculinos:60, Cantidad femeninos:85", reporteBuscado.getDescripcion());
    }

    //Metodo para simular el enlistado de todos los registros de reporte en la base de datos
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarReporteTest() {
        List<Reporte> lista = reporteRepo.findAll();
        System.out.println(lista);
    }
}
