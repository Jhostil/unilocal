package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase AministradorTest para las pruebas CRUD de la entidad Administrador
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    //Metodo para simular el registro del administrador en la base de datos
    @Test
    public void registrarAdministradorTest() {

        Administrador administradorNuevo = new Administrador("juanito@gmail.com", "juan manolo", "Juannixo12", "Elcapojuan12", 20.124561, 65.595684);
        Administrador administradorGuardado = administradorRepo.save(administradorNuevo);

        Assertions.assertNotNull(administradorGuardado);
    }

    //Metodo para simular la eliminacion de un administrador en la base de datos
    @Test
    public void eliminarAdministradorTest() {

        Administrador administradorNuevo = new Administrador("juanito@gmail.com", "juan manolo", "Juannixo12", "Elcapojuan12", 20.124561, 65.595684);
        administradorRepo.save(administradorNuevo);

        administradorRepo.delete(administradorNuevo);

        Administrador administradorBorrado = administradorRepo.findById("juanito@gmail.com").orElse(null);

        Assertions.assertNull(administradorBorrado);
    }

    //Metodo para simular el cambio de informacion de un dato existente perteneciente a un administrador en la base de datos
    @Test
    public void actualizarAdministradorTest() {

        Administrador administradorNuevo = new Administrador("juanito@gmail.com", "juan manolo", "Juannixo12", "Elcapojuan12", 20.124561, 65.595684);
        Administrador administradorGuardado = administradorRepo.save(administradorNuevo);

        administradorGuardado.setContrasena("estanosemeolvida123");
        administradorRepo.save(administradorGuardado);

        Administrador administradorBuscado = administradorRepo.findById("juanito@gmail.com").orElse(null);

        Assertions.assertEquals("estanosemeolvida123", administradorBuscado.getContrasena());
    }

    //Metodo para simular el enlistado de todos los registros de administrador en la base de datos
    @Test
    @Sql("classpath:administradores.sql")
    public void listarAdministradorTest() {
        List<Administrador> lista = administradorRepo.findAll();
        System.out.println(lista);
    }
}
