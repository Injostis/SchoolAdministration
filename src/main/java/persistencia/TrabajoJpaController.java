package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Materia;
import logica.Calificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Trabajo;
import persistencia.exceptions.NonexistentEntityException;

public class TrabajoJpaController implements Serializable {

    public TrabajoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public TrabajoJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(Trabajo trabajo) {
        if (trabajo.getListaCalificaciones() == null) {
            trabajo.setListaCalificaciones(new ArrayList<Calificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia miMateria = trabajo.getMiMateria();
            if (miMateria != null) {
                miMateria = em.getReference(miMateria.getClass(), miMateria.getId_materia());
                trabajo.setMiMateria(miMateria);
            }
            List<Calificacion> attachedListaCalificaciones = new ArrayList<Calificacion>();
            for (Calificacion listaCalificacionesCalificacionToAttach : trabajo.getListaCalificaciones()) {
                listaCalificacionesCalificacionToAttach = em.getReference(listaCalificacionesCalificacionToAttach.getClass(), listaCalificacionesCalificacionToAttach.getId_calificacion());
                attachedListaCalificaciones.add(listaCalificacionesCalificacionToAttach);
            }
            trabajo.setListaCalificaciones(attachedListaCalificaciones);
            em.persist(trabajo);
            if (miMateria != null) {
                miMateria.getListaTrabajos().add(trabajo);
                miMateria = em.merge(miMateria);
            }
            for (Calificacion listaCalificacionesCalificacion : trabajo.getListaCalificaciones()) {
                Trabajo oldMiTrabajoOfListaCalificacionesCalificacion = listaCalificacionesCalificacion.getMiTrabajo();
                listaCalificacionesCalificacion.setMiTrabajo(trabajo);
                listaCalificacionesCalificacion = em.merge(listaCalificacionesCalificacion);
                if (oldMiTrabajoOfListaCalificacionesCalificacion != null) {
                    oldMiTrabajoOfListaCalificacionesCalificacion.getListaCalificaciones().remove(listaCalificacionesCalificacion);
                    oldMiTrabajoOfListaCalificacionesCalificacion = em.merge(oldMiTrabajoOfListaCalificacionesCalificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabajo trabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajo persistentTrabajo = em.find(Trabajo.class, trabajo.getId());
            Materia miMateriaOld = persistentTrabajo.getMiMateria();
            Materia miMateriaNew = trabajo.getMiMateria();
            List<Calificacion> listaCalificacionesOld = persistentTrabajo.getListaCalificaciones();
            List<Calificacion> listaCalificacionesNew = trabajo.getListaCalificaciones();
            if (miMateriaNew != null) {
                miMateriaNew = em.getReference(miMateriaNew.getClass(), miMateriaNew.getId_materia());
                trabajo.setMiMateria(miMateriaNew);
            }
            List<Calificacion> attachedListaCalificacionesNew = new ArrayList<Calificacion>();
            for (Calificacion listaCalificacionesNewCalificacionToAttach : listaCalificacionesNew) {
                listaCalificacionesNewCalificacionToAttach = em.getReference(listaCalificacionesNewCalificacionToAttach.getClass(), listaCalificacionesNewCalificacionToAttach.getId_calificacion());
                attachedListaCalificacionesNew.add(listaCalificacionesNewCalificacionToAttach);
            }
            listaCalificacionesNew = attachedListaCalificacionesNew;
            trabajo.setListaCalificaciones(listaCalificacionesNew);
            trabajo = em.merge(trabajo);
            if (miMateriaOld != null && !miMateriaOld.equals(miMateriaNew)) {
                miMateriaOld.getListaTrabajos().remove(trabajo);
                miMateriaOld = em.merge(miMateriaOld);
            }
            if (miMateriaNew != null && !miMateriaNew.equals(miMateriaOld)) {
                miMateriaNew.getListaTrabajos().add(trabajo);
                miMateriaNew = em.merge(miMateriaNew);
            }
            for (Calificacion listaCalificacionesOldCalificacion : listaCalificacionesOld) {
                if (!listaCalificacionesNew.contains(listaCalificacionesOldCalificacion)) {
                    listaCalificacionesOldCalificacion.setMiTrabajo(null);
                    listaCalificacionesOldCalificacion = em.merge(listaCalificacionesOldCalificacion);
                }
            }
            for (Calificacion listaCalificacionesNewCalificacion : listaCalificacionesNew) {
                if (!listaCalificacionesOld.contains(listaCalificacionesNewCalificacion)) {
                    Trabajo oldMiTrabajoOfListaCalificacionesNewCalificacion = listaCalificacionesNewCalificacion.getMiTrabajo();
                    listaCalificacionesNewCalificacion.setMiTrabajo(trabajo);
                    listaCalificacionesNewCalificacion = em.merge(listaCalificacionesNewCalificacion);
                    if (oldMiTrabajoOfListaCalificacionesNewCalificacion != null && !oldMiTrabajoOfListaCalificacionesNewCalificacion.equals(trabajo)) {
                        oldMiTrabajoOfListaCalificacionesNewCalificacion.getListaCalificaciones().remove(listaCalificacionesNewCalificacion);
                        oldMiTrabajoOfListaCalificacionesNewCalificacion = em.merge(oldMiTrabajoOfListaCalificacionesNewCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = trabajo.getId();
                if (findTrabajo(id) == null) {
                    throw new NonexistentEntityException("The trabajo with id " + id + " no longer exists.");
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
            Trabajo trabajo;
            try {
                trabajo = em.getReference(Trabajo.class, id);
                trabajo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajo with id " + id + " no longer exists.", enfe);
            }
            Materia miMateria = trabajo.getMiMateria();
            if (miMateria != null) {
                miMateria.getListaTrabajos().remove(trabajo);
                miMateria = em.merge(miMateria);
            }
            List<Calificacion> listaCalificaciones = trabajo.getListaCalificaciones();
            for (Calificacion listaCalificacionesCalificacion : listaCalificaciones) {
                listaCalificacionesCalificacion.setMiTrabajo(null);
                listaCalificacionesCalificacion = em.merge(listaCalificacionesCalificacion);
            }
            em.remove(trabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabajo> findTrabajoEntities() {
        return findTrabajoEntities(true, -1, -1);
    }

    public List<Trabajo> findTrabajoEntities(int maxResults, int firstResult) {
        return findTrabajoEntities(false, maxResults, firstResult);
    }

    private List<Trabajo> findTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajo.class));
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

    public Trabajo findTrabajo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajo> rt = cq.from(Trabajo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
