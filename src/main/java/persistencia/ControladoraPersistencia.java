package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Maestro;
import logica.Estudiante;
import logica.Grupo;
import logica.Materia;
import logica.Trabajo;
import logica.TipoTrabajo;
import logica.Calificacion;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    
    MaestroJpaController maestroJPA = new MaestroJpaController();
    EstudianteJpaController estudianteJPA = new EstudianteJpaController();
    GrupoJpaController grupoJPA = new GrupoJpaController();
    MateriaJpaController materiaJPA = new MateriaJpaController();
    TrabajoJpaController trabajoJPA = new TrabajoJpaController();
    TipoTrabajoJpaController tipoTrabajoJPA = new TipoTrabajoJpaController();
    CalificacionJpaController calificacionJPA = new CalificacionJpaController();

    
    //CREATE
    public void crearMaestro(Maestro maestro) {
        maestroJPA.create(maestro);
    }
    
    public void crearEstudiante(Estudiante estudiante) {
        estudianteJPA.create(estudiante);
    }
    
    public void crearGrupo(Grupo grupo) {
        grupoJPA.create(grupo);
    }
    
    public void crearMateria(Materia materia) {
        materiaJPA.create(materia);
    }
    
    public void crearTrabajo(Trabajo trabajo) {
        trabajoJPA.create(trabajo);
    }
    
    public void crearTipoTrabajo(TipoTrabajo tipoTrabajo) {
        tipoTrabajoJPA.create(tipoTrabajo);
    }
    
    public void crearCalificacion(Calificacion calificacion) {
        calificacionJPA.create(calificacion);
    }

    //READ
    public List<Maestro> traerMaestros() {
        return maestroJPA.findMaestroEntities();
    }
    
    public List<Estudiante> traerEstudiantes() {
        return estudianteJPA.findEstudianteEntities();
    }
    
    public List<Grupo> traerGrupos() {
        return grupoJPA.findGrupoEntities();
    }
    
    public List<Materia> traerMaterias() {
        return materiaJPA.findMateriaEntities();
    }
    
    public List<Trabajo> traerTrabajos() {
        return trabajoJPA.findTrabajoEntities();
    }
    
    public List<TipoTrabajo> traerTipoTrabajos() {
        return tipoTrabajoJPA.findTipoTrabajoEntities();
    }
    
    public List<Calificacion> traerCalificaciones() {
        return calificacionJPA.findCalificacionEntities();
    }
    
    //UPDATE
    
    public Maestro traerMaestro(Long id) {
        return maestroJPA.findMaestro(id);
    }
    
    public void editarMaestro(Maestro maestro) {
        try {
            maestroJPA.edit(maestro);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Estudiante traerEstudiante(Long id) {
        return estudianteJPA.findEstudiante(id);
    }
    
    public void editarEstudiante(Estudiante estudiante) {
        try {
            estudianteJPA.edit(estudiante);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Grupo traerGrupo(Long id) {
        return grupoJPA.findGrupo(id);
    }
    
    public void editarGrupo(Grupo grupo) {
        try {
            grupoJPA.edit(grupo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Materia traerMateria(Long id) {
        return materiaJPA.findMateria(id);
    }
    
    public void editarMateria(Materia materia) {
        try {
            materiaJPA.edit(materia);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Trabajo traerTrabajo(Long id) {
        return trabajoJPA.findTrabajo(id);
    }
    
    public void editarTrabajo(Trabajo trabajo) {
        try {
            trabajoJPA.edit(trabajo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TipoTrabajo traerTipoTrabajo(Long id) {
        return tipoTrabajoJPA.findTipoTrabajo(id);
    }
    
    public void editarTipoTrabajo(TipoTrabajo tipoTrabajo) {
        try {
            tipoTrabajoJPA.edit(tipoTrabajo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Calificacion traerCalificacion(Long id) {
        return calificacionJPA.findCalificacion(id);
    }
    
    public void editarCalificacion(Calificacion calificacion) {
        try {
            calificacionJPA.edit(calificacion);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //DELETE

    public void borrarMaestro(Long id) {
        try {
            maestroJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarEstudiante(Long id) {
        try {
            estudianteJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarGrupo(Long id) {
        try {
            grupoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarMateria(Long id) {
        try {
            materiaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarTrabajo(Long id) {
        try {
            trabajoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarTipoTrabajo(Long id) {
        try {
            tipoTrabajoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarCalificacion(Long id) {
        try {
            calificacionJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
