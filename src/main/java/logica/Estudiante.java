package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Estudiante implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estudiante;
    
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    
    @ManyToOne
    @JoinColumn(name = "miGrupo")
    private Grupo miGrupo;
    
    @OneToMany(mappedBy = "miEstudiante")
    private List<Calificacion> listaCalificaciones;

    public Estudiante() {
    }

    public Estudiante(Long id_estudiante, String nombre, String apellido_paterno, String apellido_materno, Grupo miGrupo, List<Calificacion> listaCalificaciones) {
        this.id_estudiante = id_estudiante;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.miGrupo = miGrupo;
        this.listaCalificaciones = listaCalificaciones;
    }

    public Long getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Long id_estudiante) {
        this.id_estudiante = id_estudiante;
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

    public Grupo getMiGrupo() {
        return miGrupo;
    }

    public void setMiGrupo(Grupo miGrupo) {
        this.miGrupo = miGrupo;
    }

    public List<Calificacion> getListaCalificaciones() {
        return listaCalificaciones;
    }

    public void setListaCalificaciones(List<Calificacion> listaCalificaciones) {
        this.listaCalificaciones = listaCalificaciones;
    }          
}
