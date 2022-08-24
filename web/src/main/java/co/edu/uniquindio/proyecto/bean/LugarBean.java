package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    private Lugar lugar;
    private Telefono telefono;

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final CategoriaServicio categoriaServicio;
    private final UsuarioServicio usuarioServicio;


    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<Imagen> imagenes;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Categoria> categorias;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;



    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio, CategoriaServicio categoriaServicio, TelefonoServicio telefonoServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.categoriaServicio = categoriaServicio;

    }

    @PostConstruct
    public void inicializar() {
        this.lugar = new Lugar();
        this.telefono = new Telefono();
        this.ciudades = ciudadServicio.listarCiudades();
        this.categorias = lugarServicio.listarCategorias();
        this.imagenes = new ArrayList<>();

    }

    public String crearLugar() {
        try {
            if(personaLogin != null){
                if (lugar.getLatitud() != null && lugar.getLongitud() != null && lugar.getLatitud() != 0 && lugar.getLongitud() != 0) {
                    lugar.setUsuario((Usuario) personaLogin);
                    lugar.setImagen(imagenes);


                    Lugar l = lugarServicio.crearLugar(lugar);

                    lugarServicio.RegistrarLugar(l);




                    telefono.setLugar(l);
                    lugarServicio.crearTelefono(telefono);
                    for (int i = 0; i < imagenes.size(); i++) {

                        imagenes.get(i).setLugar(l);
                        lugarServicio.crearImagen(imagenes.get(i));

                    }


                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El lugar ha sido creado");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);

                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", " Es necesario ubicar el lugar dentro del mapa");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);

                }

            }

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen != null) {
            imagenes.add(new Imagen(nombreImagen));
        }
    }

    public String subirImagen(UploadedFile file) {
        try {
            InputStream input = file.getInputStream();
            String filename = FilenameUtils.getName(file.getFileName());
            String basename = FilenameUtils.getBaseName(filename) + "_";
            String extension = "." + FilenameUtils.getExtension(filename);
            File fileDest = File.createTempFile(basename, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);
            return fileDest.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }
}
