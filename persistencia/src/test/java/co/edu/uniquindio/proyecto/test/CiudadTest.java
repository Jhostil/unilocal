package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/*
Clase que sirve para hacer los respectivas pruebas unitarias del CRUD de la entidad Ciudad
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    //Atributo que referencia al repositorio de datos. el @Autowired sirve para que se autoinicialice el atributo
    @Autowired
    private CiudadRepo ciudadRepo;

    //Método de prueba para registrar una ciudad
    @Test
    public void registrarCiudadTest() {

        Ciudad ciudadNueva = new Ciudad("Armenia");
        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);

        Assertions.assertNotNull(ciudadGuardada);
    }

    //Método de prueba para eliminar una ciudad
    @Test
    public void eliminarCiudadTest() {

        Ciudad ciudadNueva = new Ciudad("Circasia");
        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);
        int llave = ciudadGuardada.getCodigo();

        ciudadRepo.delete(ciudadNueva);

        Ciudad ciudadBorrada = ciudadRepo.findById(llave).orElse(null);

        Assertions.assertNull(ciudadBorrada);
    }

    //Método de prueba para actualizar en nombre de una Ciudad
    @Test
    public void actualizarCiudadTest() {

        Ciudad ciudadNueva = new Ciudad("Calarcá");
        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);
        int llave = ciudadGuardada.getCodigo();

        ciudadGuardada.setNombre("Quimbaya");
        ciudadRepo.save(ciudadGuardada);

        Ciudad ciudadBuscada = ciudadRepo.findById(llave).orElse(null);

        Assertions.assertEquals("Quimbaya", ciudadBuscada.getNombre());
    }

    //Método de prueba para listar los datos de una base de datos de sql
    @Test
    @Sql("classpath:ciudades.sql")
    public void listarCiudadesTest() {
        List<Ciudad> lista = ciudadRepo.findAll();
        System.out.println(lista);
    }
}
