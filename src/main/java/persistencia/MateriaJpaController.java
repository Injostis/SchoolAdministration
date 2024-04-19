package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Grupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Maestro;
import logica.Materia;
import logica.Trabajo;
import persistencia.exceptions.NonexistentEntityException;

public class MateriaJpaController implements Serializable {

    public MateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public MateriaJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(Materia materia) {
        if (materia.getListaGrupos() == null) {
            materia.setListaGrupos(new ArrayList<Grupo>());
        }
        if (materia.getListaMaestros() == null) {
            materia.setListaMaestros(new ArrayList<Maestro>());
        }
        if (materia.getListaTrabajos() == null) {
            materia.setListaTrabajos(new ArrayList<Trabajo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Grupo> attachedListaGrupos = new ArrayList<Grupo>();
            for (Grupo listaGruposGrupoToAttach : materia.getListaGrupos()) {
                listaGruposGrupoToAttach = em.getReference(listaGruposGrupoToAttach.getClass(), listaGruposGrupoToAttach.getId_grupo());
                attachedListaGrupos.add(listaGruposGrupoToAttach);
            }
            materia.setListaGrupos(attachedListaGrupos);
            List<Maestro> attachedListaMaestros = new ArrayList<Maestro>();
            for (Maestro listaMaestrosMaestroToAttach : materia.getListaMaestros()) {
                listaMaestrosMaestroToAttach = em.getReference(listaMaestrosMaestroToAttach.getClass(), listaMaestrosMaestroToAttach.getId_maestro());
                attachedListaMaestros.add(listaMaestrosMaestroToAttach);
            }
            materia.setListaMaestros(attachedListaMaestros);
            List<Trabajo> attachedListaTrabajos = new ArrayList<Trabajo>();
            for (Trabajo listaTrabajosTrabajoToAttach : materia.getListaTrabajos()) {
                listaTrabajosTrabajoToAttach = em.getReference(listaTrabajosTrabajoToAttach.getClass(), listaTrabajosTrabajoToAttach.getId());
                attachedListaTrabajos.add(listaTrabajosTrabajoToAttach);
            }
            materia.setListaTrabajos(attachedListaTrabajos);
            em.persist(materia);
            for (Grupo listaGruposGrupo : materia.getListaGrupos()) {
                listaGruposGrupo.getListaMaterias().add(materia);
                listaGruposGrupo = em.merge(listaGruposGrupo);
            }
            for (Maestro listaMaestrosMaestro : materia.getListaMaestros()) {
                listaMaestrosMaestro.getListaMaterias().add(materia);
                listaMaestrosMaestro = em.merge(listaMaestrosMaestro);
            }
            for (Trabajo listaTrabajosTrabajo : materia.getListaTrabajos()) {
                Materia oldMiMateriaOfListaTrabajosTrabajo = listaTrabajosTrabajo.getMiMateria();
                listaTrabajosTrabajo.setMiMateria(materia);
                listaTrabajosTrabajo = em.merge(listaTrabajosTrabajo);
                if (oldMiMateriaOfListaTrabajosTrabajo != null) {
                    oldMiMateriaOfListaTrabajosTrabajo.getListaTrabajos().remove(listaTrabajosTrabajo);
                    oldMiMateriaOfListaTrabajosTrabajo = em.merge(oldMiMateriaOfListaTrabajosTrabajo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getId_materia());
            List<Grupo> listaGruposOld = persistentMateria.getListaGrupos();
            List<Grupo> listaGruposNew = materia.getListaGrupos();
            List<Maestro> listaMaestrosOld = persistentMateria.getListaMaestros();
            List<Maestro> listaMaestrosNew = materia.getListaMaestros();
            List<Trabajo> listaTrabajosOld = persistentMateria.getListaTrabajos();
            List<Trabajo> listaTrabajosNew = materia.getListaTrabajos();
            List<Grupo> attachedListaGruposNew = new ArrayList<Grupo>();
            for (Grupo listaGruposNewGrupoToAttach : listaGruposNew) {
                listaGruposNewGrupoToAttach = em.getReference(listaGruposNewGrupoToAttach.getClass(), listaGruposNewGrupoToAttach.getId_grupo());
                attachedListaGruposNew.add(listaGruposNewGrupoToAttach);
            }
            listaGruposNew = attachedListaGruposNew;
            materia.setListaGrupos(listaGruposNew);
            List<Maestro> attachedListaMaestrosNew = new ArrayList<Maestro>();
            for (Maestro listaMaestrosNewMaestroToAttach : listaMaestrosNew) {
                listaMaestrosNewMaestroToAttach = em.getReference(listaMaestrosNewMaestroToAttach.getClass(), listaMaestrosNewMaestroToAttach.getId_maestro());
                attachedListaMaestrosNew.add(listaMaestrosNewMaestroToAttach);
            }
            listaMaestrosNew = attachedListaMaestrosNew;
            materia.setListaMaestros(listaMaestrosNew);
            List<Trabajo> attachedListaTrabajosNew = new ArrayList<Trabajo>();
            for (Trabajo listaTrabajosNewTrabajoToAttach : listaTrabajosNew) {
                listaTrabajosNewTrabajoToAttach = em.getReference(listaTrabajosNewTrabajoToAttach.getClass(), listaTrabajosNewTrabajoToAttach.getId());
                attachedListaTrabajosNew.add(listaTrabajosNewTrabajoToAttach);
            }
            listaTrabajosNew = attachedListaTrabajosNew;
            materia.setListaTrabajos(listaTrabajosNew);
            materia = em.merge(materia);
            for (Grupo listaGruposOldGrupo : listaGruposOld) {
                if (!listaGruposNew.contains(listaGruposOldGrupo)) {
                    listaGruposOldGrupo.getListaMaterias().remove(materia);
                    listaGruposOldGrupo = em.merge(listaGruposOldGrupo);
                }
            }
            for (Grupo listaGruposNewGrupo : listaGruposNew) {
                if (!listaGruposOld.contains(listaGruposNewGrupo)) {
                    listaGruposNewGrupo.getListaMaterias().add(materia);
                    listaGruposNewGrupo = em.merge(listaGruposNewGrupo);
                }
            }
            for (Maestro listaMaestrosOldMaestro : listaMaestrosOld) {
                if (!listaMaestrosNew.contains(listaMaestrosOldMaestro)) {
                    listaMaestrosOldMaestro.getListaMaterias().remove(materia);
                    listaMaestrosOldMaestro = em.merge(listaMaestrosOldMaestro);
                }
            }
            for (Maestro listaMaestrosNewMaestro : listaMaestrosNew) {
                if (!listaMaestrosOld.contains(listaMaestrosNewMaestro)) {
                    listaMaestrosNewMaestro.getListaMaterias().add(materia);
                    listaMaestrosNewMaestro = em.merge(listaMaestrosNewMaestro);
                }
            }
            for (Trabajo listaTrabajosOldTrabajo : listaTrabajosOld) {
                if (!listaTrabajosNew.contains(listaTrabajosOldTrabajo)) {
                    listaTrabajosOldTrabajo.setMiMateria(null);
                    listaTrabajosOldTrabajo = em.merge(listaTrabajosOldTrabajo);
                }
            }
            for (Trabajo listaTrabajosNewTrabajo : listaTrabajosNew) {
                if (!listaTrabajosOld.contains(listaTrabajosNewTrabajo)) {
                    Materia oldMiMateriaOfListaTrabajosNewTrabajo = listaTrabajosNewTrabajo.getMiMateria();
                    listaTrabajosNewTrabajo.setMiMateria(materia);
                    listaTrabajosNewTrabajo = em.merge(listaTrabajosNewTrabajo);
                    if (oldMiMateriaOfListaTrabajosNewTrabajo != null && !oldMiMateriaOfListaTrabajosNewTrabajo.equals(materia)) {
                        oldMiMateriaOfListaTrabajosNewTrabajo.getListaTrabajos().remove(listaTrabajosNewTrabajo);
                        oldMiMateriaOfListaTrabajosNewTrabajo = em.merge(oldMiMateriaOfListaTrabajosNewTrabajo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = materia.getId_materia();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
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
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getId_materia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
            }
            List<Grupo> listaGrupos = materia.getListaGrupos();
            for (Grupo listaGruposGrupo : listaGrupos) {
                listaGruposGrupo.getListaMaterias().remove(materia);
                listaGruposGrupo = em.merge(listaGruposGrupo);
            }
            List<Maestro> listaMaestros = materia.getListaMaestros();
            for (Maestro listaMaestrosMaestro : listaMaestros) {
                listaMaestrosMaestro.getListaMaterias().remove(materia);
                listaMaestrosMaestro = em.merge(listaMaestrosMaestro);
            }
            List<Trabajo> listaTrabajos = materia.getListaTrabajos();
            for (Trabajo listaTrabajosTrabajo : listaTrabajos) {
                listaTrabajosTrabajo.setMiMateria(null);
                listaTrabajosTrabajo = em.merge(listaTrabajosTrabajo);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
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

    public Materia findMateria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
