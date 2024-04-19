package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Calificacion;
import logica.Estudiante;
import logica.Trabajo;
import persistencia.exceptions.NonexistentEntityException;

public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public CalificacionJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(Calificacion calificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante miEstudiante = calificacion.getMiEstudiante();
            if (miEstudiante != null) {
                miEstudiante = em.getReference(miEstudiante.getClass(), miEstudiante.getId_estudiante());
                calificacion.setMiEstudiante(miEstudiante);
            }
            Trabajo miTrabajo = calificacion.getMiTrabajo();
            if (miTrabajo != null) {
                miTrabajo = em.getReference(miTrabajo.getClass(), miTrabajo.getId());
                calificacion.setMiTrabajo(miTrabajo);
            }
            em.persist(calificacion);
            if (miEstudiante != null) {
                miEstudiante.getListaCalificaciones().add(calificacion);
                miEstudiante = em.merge(miEstudiante);
            }
            if (miTrabajo != null) {
                miTrabajo.getListaCalificaciones().add(calificacion);
                miTrabajo = em.merge(miTrabajo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getId_calificacion());
            Estudiante miEstudianteOld = persistentCalificacion.getMiEstudiante();
            Estudiante miEstudianteNew = calificacion.getMiEstudiante();
            Trabajo miTrabajoOld = persistentCalificacion.getMiTrabajo();
            Trabajo miTrabajoNew = calificacion.getMiTrabajo();
            if (miEstudianteNew != null) {
                miEstudianteNew = em.getReference(miEstudianteNew.getClass(), miEstudianteNew.getId_estudiante());
                calificacion.setMiEstudiante(miEstudianteNew);
            }
            if (miTrabajoNew != null) {
                miTrabajoNew = em.getReference(miTrabajoNew.getClass(), miTrabajoNew.getId());
                calificacion.setMiTrabajo(miTrabajoNew);
            }
            calificacion = em.merge(calificacion);
            if (miEstudianteOld != null && !miEstudianteOld.equals(miEstudianteNew)) {
                miEstudianteOld.getListaCalificaciones().remove(calificacion);
                miEstudianteOld = em.merge(miEstudianteOld);
            }
            if (miEstudianteNew != null && !miEstudianteNew.equals(miEstudianteOld)) {
                miEstudianteNew.getListaCalificaciones().add(calificacion);
                miEstudianteNew = em.merge(miEstudianteNew);
            }
            if (miTrabajoOld != null && !miTrabajoOld.equals(miTrabajoNew)) {
                miTrabajoOld.getListaCalificaciones().remove(calificacion);
                miTrabajoOld = em.merge(miTrabajoOld);
            }
            if (miTrabajoNew != null && !miTrabajoNew.equals(miTrabajoOld)) {
                miTrabajoNew.getListaCalificaciones().add(calificacion);
                miTrabajoNew = em.merge(miTrabajoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calificacion.getId_calificacion();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getId_calificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            Estudiante miEstudiante = calificacion.getMiEstudiante();
            if (miEstudiante != null) {
                miEstudiante.getListaCalificaciones().remove(calificacion);
                miEstudiante = em.merge(miEstudiante);
            }
            Trabajo miTrabajo = calificacion.getMiTrabajo();
            if (miTrabajo != null) {
                miTrabajo.getListaCalificaciones().remove(calificacion);
                miTrabajo = em.merge(miTrabajo);
            }
            em.remove(calificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calificacion findCalificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificacion> rt = cq.from(Calificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
