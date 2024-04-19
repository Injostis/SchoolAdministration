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
import logica.TipoTrabajo;
import persistencia.exceptions.NonexistentEntityException;

public class TipoTrabajoJpaController implements Serializable {

    public TipoTrabajoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public TipoTrabajoJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(TipoTrabajo tipoTrabajo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoTrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTrabajo tipoTrabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoTrabajo = em.merge(tipoTrabajo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoTrabajo.getId();
                if (findTipoTrabajo(id) == null) {
                    throw new NonexistentEntityException("The tipoTrabajo with id " + id + " no longer exists.");
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
            TipoTrabajo tipoTrabajo;
            try {
                tipoTrabajo = em.getReference(TipoTrabajo.class, id);
                tipoTrabajo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTrabajo with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoTrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTrabajo> findTipoTrabajoEntities() {
        return findTipoTrabajoEntities(true, -1, -1);
    }

    public List<TipoTrabajo> findTipoTrabajoEntities(int maxResults, int firstResult) {
        return findTipoTrabajoEntities(false, maxResults, firstResult);
    }

    private List<TipoTrabajo> findTipoTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoTrabajo.class));
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

    public TipoTrabajo findTipoTrabajo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoTrabajo> rt = cq.from(TipoTrabajo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
