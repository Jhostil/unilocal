package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//archivo test para realizar las pruebas (CRUD)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {
    @Autowired
    private LugarRepo lugarRepo;


    //metodo para registrar un lugar
    @Test
    public void registrarLugarTest() {

        Lugar lugarNuevo = new Lugar("restaurante de mariscos", "mariamar", "carrera 20", 20.177776, 34.000564);
        Lugar lugarGuardada = lugarRepo.save(lugarNuevo);

        Assertions.assertNotNull(lugarGuardada);
    }

    //metodo para eliminar un lugar
    @Test
    public void eliminarLugarTest() {

        Lugar lugarNuevo = new Lugar("restaurante de mariscos", "mariamar", "carrera 20", 20.177776, 34.000564);
        lugarRepo.save(lugarNuevo);

        lugarRepo.delete(lugarNuevo);

        Lugar lugarBorrado = lugarRepo.findById(lugarNuevo.getId()).orElse(null);

        Assertions.assertNull(lugarBorrado);
    }

    //metodo para actualziar un lugar
    @Test
    public void actualizarLugarTest() {

        Lugar lugarNuevo = new Lugar("restaurante de mariscos", "mariamar", "carrera 20", 20.177776, 34.000564);
        Lugar lugarGuardado = lugarRepo.save(lugarNuevo);

        lugarGuardado.setDescripcion("fabulosa comida arabe");
        lugarRepo.save(lugarGuardado);

        Lugar lugarBuscado = lugarRepo.findById(lugarGuardado.getId()).orElse(null);

        Assertions.assertEquals("fabulosa comida arabe", lugarBuscado.getDescripcion());
    }

    //nombre de clase en sql
    //añadir datos a la tabla
    @Test
    @Sql("classpath:lugares.sql")
    public void listarUsuariosTest() {
        List<Lugar> lista = lugarRepo.findAll();
        System.out.println(lista);
    }
}
