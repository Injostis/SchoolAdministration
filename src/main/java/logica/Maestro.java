package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Maestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_maestro;

    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String username;
    private String password;
    
    @ManyToMany(mappedBy = "listaMaestros")
    private List<Materia> listaMaterias;

    public Maestro() {
    }

    public Maestro(Long id_maestro, String nombre, String apellido_paterno, String apellido_materno, String username, String password, List<Materia> listaMaterias) {
        this.id_maestro = id_maestro;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.username = username;
        this.password = password;
        this.listaMaterias = listaMaterias;
    }

    public Long getId_maestro() {
        return id_maestro;
    }

    public void setId_maestro(Long id_maestro) {
        this.id_maestro = id_maestro;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
}
