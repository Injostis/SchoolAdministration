package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Grupo;
import logica.Calificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Estudiante;
import persistencia.exceptions.NonexistentEntityException;

public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public EstudianteJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(Estudiante estudiante) {
        if (estudiante.getListaCalificaciones() == null) {
            estudiante.setListaCalificaciones(new ArrayList<Calificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo miGrupo = estudiante.getMiGrupo();
            if (miGrupo != null) {
                miGrupo = em.getReference(miGrupo.getClass(), miGrupo.getId_grupo());
                estudiante.setMiGrupo(miGrupo);
            }
            List<Calificacion> attachedListaCalificaciones = new ArrayList<Calificacion>();
            for (Calificacion listaCalificacionesCalificacionToAttach : estudiante.getListaCalificaciones()) {
                listaCalificacionesCalificacionToAttach = em.getReference(listaCalificacionesCalificacionToAttach.getClass(), listaCalificacionesCalificacionToAttach.getId_calificacion());
                attachedListaCalificaciones.add(listaCalificacionesCalificacionToAttach);
            }
            estudiante.setListaCalificaciones(attachedListaCalificaciones);
            em.persist(estudiante);
            if (miGrupo != null) {
                miGrupo.getListaEstudiantes().add(estudiante);
                miGrupo = em.merge(miGrupo);
            }
            for (Calificacion listaCalificacionesCalificacion : estudiante.getListaCalificaciones()) {
                Estudiante oldMiEstudianteOfListaCalificacionesCalificacion = listaCalificacionesCalificacion.getMiEstudiante();
                listaCalificacionesCalificacion.setMiEstudiante(estudiante);
                listaCalificacionesCalificacion = em.merge(listaCalificacionesCalificacion);
                if (oldMiEstudianteOfListaCalificacionesCalificacion != null) {
                    oldMiEstudianteOfListaCalificacionesCalificacion.getListaCalificaciones().remove(listaCalificacionesCalificacion);
                    oldMiEstudianteOfListaCalificacionesCalificacion = em.merge(oldMiEstudianteOfListaCalificacionesCalificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getId_estudiante());
            Grupo miGrupoOld = persistentEstudiante.getMiGrupo();
            Grupo miGrupoNew = estudiante.getMiGrupo();
            List<Calificacion> listaCalificacionesOld = persistentEstudiante.getListaCalificaciones();
            List<Calificacion> listaCalificacionesNew = estudiante.getListaCalificaciones();
            if (miGrupoNew != null) {
                miGrupoNew = em.getReference(miGrupoNew.getClass(), miGrupoNew.getId_grupo());
                estudiante.setMiGrupo(miGrupoNew);
            }
            List<Calificacion> attachedListaCalificacionesNew = new ArrayList<Calificacion>();
            for (Calificacion listaCalificacionesNewCalificacionToAttach : listaCalificacionesNew) {
                listaCalificacionesNewCalificacionToAttach = em.getReference(listaCalificacionesNewCalificacionToAttach.getClass(), listaCalificacionesNewCalificacionToAttach.getId_calificacion());
                attachedListaCalificacionesNew.add(listaCalificacionesNewCalificacionToAttach);
            }
            listaCalificacionesNew = attachedListaCalificacionesNew;
            estudiante.setListaCalificaciones(listaCalificacionesNew);
            estudiante = em.merge(estudiante);
            if (miGrupoOld != null && !miGrupoOld.equals(miGrupoNew)) {
                miGrupoOld.getListaEstudiantes().remove(estudiante);
                miGrupoOld = em.merge(miGrupoOld);
            }
            if (miGrupoNew != null && !miGrupoNew.equals(miGrupoOld)) {
                miGrupoNew.getListaEstudiantes().add(estudiante);
                miGrupoNew = em.merge(miGrupoNew);
            }
            for (Calificacion listaCalificacionesOldCalificacion : listaCalificacionesOld) {
                if (!listaCalificacionesNew.contains(listaCalificacionesOldCalificacion)) {
                    listaCalificacionesOldCalificacion.setMiEstudiante(null);
                    listaCalificacionesOldCalificacion = em.merge(listaCalificacionesOldCalificacion);
                }
            }
            for (Calificacion listaCalificacionesNewCalificacion : listaCalificacionesNew) {
                if (!listaCalificacionesOld.contains(listaCalificacionesNewCalificacion)) {
                    Estudiante oldMiEstudianteOfListaCalificacionesNewCalificacion = listaCalificacionesNewCalificacion.getMiEstudiante();
                    listaCalificacionesNewCalificacion.setMiEstudiante(estudiante);
                    listaCalificacionesNewCalificacion = em.merge(listaCalificacionesNewCalificacion);
                    if (oldMiEstudianteOfListaCalificacionesNewCalificacion != null && !oldMiEstudianteOfListaCalificacionesNewCalificacion.equals(estudiante)) {
                        oldMiEstudianteOfListaCalificacionesNewCalificacion.getListaCalificaciones().remove(listaCalificacionesNewCalificacion);
                        oldMiEstudianteOfListaCalificacionesNewCalificacion = em.merge(oldMiEstudianteOfListaCalificacionesNewCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estudiante.getId_estudiante();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getId_estudiante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            Grupo miGrupo = estudiante.getMiGrupo();
            if (miGrupo != null) {
                miGrupo.getListaEstudiantes().remove(estudiante);
                miGrupo = em.merge(miGrupo);
            }
            List<Calificacion> listaCalificaciones = estudiante.getListaCalificaciones();
            for (Calificacion listaCalificacionesCalificacion : listaCalificaciones) {
                listaCalificacionesCalificacion.setMiEstudiante(null);
                listaCalificacionesCalificacion = em.merge(listaCalificacionesCalificacion);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
