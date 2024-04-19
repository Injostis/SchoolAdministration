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
import javax.persistence.OneToOne;


@Entity
public class Trabajo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;
    
    @OneToOne
    @JoinColumn(name = "miTipoTrabajo")
    private TipoTrabajo miTipoTrabajo;
    
    @ManyToOne
    @JoinColumn(name = "miMateria")
    private Materia miMateria;
    
    @OneToMany(mappedBy = "miTrabajo")
    private List<Calificacion> listaCalificaciones;

    public Trabajo() {
    }

    public Trabajo(Long id, String nombre, String descripcion, TipoTrabajo miTipoTrabajo, Materia miMateria, List<Calificacion> listaCalificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.miTipoTrabajo = miTipoTrabajo;
        this.miMateria = miMateria;
        this.listaCalificaciones = listaCalificaciones;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoTrabajo getMiTipoTrabajo() {
        return miTipoTrabajo;
    }

    public void setMiTipoTrabajo(TipoTrabajo miTipoTrabajo) {
        this.miTipoTrabajo = miTipoTrabajo;
    }

    public Materia getMiMateria() {
        return miMateria;
    }

    public void setMiMateria(Materia miMateria) {
        this.miMateria = miMateria;
    }

    public List<Calificacion> getListaCalificaciones() {
        return listaCalificaciones;
    }

    public void setListaCalificaciones(List<Calificacion> listaCalificaciones) {
        this.listaCalificaciones = listaCalificaciones;
    }
    
}
