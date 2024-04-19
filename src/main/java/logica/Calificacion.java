package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Calificacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_calificacion;
    
    private float valor;
    
    @ManyToOne
    @JoinColumn(name = "miEstudiante")
    private Estudiante miEstudiante;
    
    @ManyToOne
    @JoinColumn(name = "miTrabajo")
    private Trabajo miTrabajo;

    public Calificacion() {
    }

    public Calificacion(Long id_calificacion, float valor, Estudiante miEstudiante, Trabajo miTrabajo) {
        this.id_calificacion = id_calificacion;
        this.valor = valor;
        this.miEstudiante = miEstudiante;
        this.miTrabajo = miTrabajo;
    }

    public Long getId_calificacion() {
        return id_calificacion;
    }

    public void setId_calificacion(Long id_calificacion) {
        this.id_calificacion = id_calificacion;
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public Estudiante getMiEstudiante() {
        return miEstudiante;
    }

    public void setMiEstudiante(Estudiante miEstudiante) {
        this.miEstudiante = miEstudiante;
    }

    public Trabajo getMiTrabajo() {
        return miTrabajo;
    }

    public void setMiTrabajo(Trabajo miTrabajo) {
        this.miTrabajo = miTrabajo;
    }
    
}
