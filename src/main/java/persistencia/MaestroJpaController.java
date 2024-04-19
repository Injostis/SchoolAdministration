package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Maestro;
import persistencia.exceptions.NonexistentEntityException;

public class MaestroJpaController implements Serializable {

    public MaestroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public MaestroJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(Maestro maestro) {
        if (maestro.getListaMaterias() == null) {
            maestro.setListaMaterias(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Materia> attachedListaMaterias = new ArrayList<Materia>();
            for (Materia listaMateriasMateriaToAttach : maestro.getListaMaterias()) {
                listaMateriasMateriaToAttach = em.getReference(listaMateriasMateriaToAttach.getClass(), listaMateriasMateriaToAttach.getId_materia());
                attachedListaMaterias.add(listaMateriasMateriaToAttach);
            }
            maestro.setListaMaterias(attachedListaMaterias);
            em.persist(maestro);
            for (Materia listaMateriasMateria : maestro.getListaMaterias()) {
                listaMateriasMateria.getListaMaestros().add(maestro);
                listaMateriasMateria = em.merge(listaMateriasMateria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maestro maestro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maestro persistentMaestro = em.find(Maestro.class, maestro.getId_maestro());
            List<Materia> listaMateriasOld = persistentMaestro.getListaMaterias();
            List<Materia> listaMateriasNew = maestro.getListaMaterias();
            List<Materia> attachedListaMateriasNew = new ArrayList<Materia>();
            for (Materia listaMateriasNewMateriaToAttach : listaMateriasNew) {
                listaMateriasNewMateriaToAttach = em.getReference(listaMateriasNewMateriaToAttach.getClass(), listaMateriasNewMateriaToAttach.getId_materia());
                attachedListaMateriasNew.add(listaMateriasNewMateriaToAttach);
            }
            listaMateriasNew = attachedListaMateriasNew;
            maestro.setListaMaterias(listaMateriasNew);
            maestro = em.merge(maestro);
            for (Materia listaMateriasOldMateria : listaMateriasOld) {
                if (!listaMateriasNew.contains(listaMateriasOldMateria)) {
                    listaMateriasOldMateria.getListaMaestros().remove(maestro);
                    listaMateriasOldMateria = em.merge(listaMateriasOldMateria);
                }
            }
            for (Materia listaMateriasNewMateria : listaMateriasNew) {
                if (!listaMateriasOld.contains(listaMateriasNewMateria)) {
                    listaMateriasNewMateria.getListaMaestros().add(maestro);
                    listaMateriasNewMateria = em.merge(listaMateriasNewMateria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = maestro.getId_maestro();
                if (findMaestro(id) == null) {
                    throw new NonexistentEntityException("The maestro with id " + id + " no longer exists.");
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
            Maestro maestro;
            try {
                maestro = em.getReference(Maestro.class, id);
                maestro.getId_maestro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maestro with id " + id + " no longer exists.", enfe);
            }
            List<Materia> listaMaterias = maestro.getListaMaterias();
            for (Materia listaMateriasMateria : listaMaterias) {
                listaMateriasMateria.getListaMaestros().remove(maestro);
                listaMateriasMateria = em.merge(listaMateriasMateria);
            }
            em.remove(maestro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maestro> findMaestroEntities() {
        return findMaestroEntities(true, -1, -1);
    }

    public List<Maestro> findMaestroEntities(int maxResults, int firstResult) {
        return findMaestroEntities(false, maxResults, firstResult);
    }

    private List<Maestro> findMaestroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maestro.class));
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

    public Maestro findMaestro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maestro.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaestroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maestro> rt = cq.from(Maestro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
