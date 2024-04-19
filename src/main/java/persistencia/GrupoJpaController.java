package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Estudiante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Grupo;
import logica.Materia;
import persistencia.exceptions.NonexistentEntityException;

public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public GrupoJpaController() {
        emf = Persistence.createEntityManagerFactory("SchoolAdministration_PU");
    }

    public void create(Grupo grupo) {
        if (grupo.getListaEstudiantes() == null) {
            grupo.setListaEstudiantes(new ArrayList<Estudiante>());
        }
        if (grupo.getListaMaterias() == null) {
            grupo.setListaMaterias(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedListaEstudiantes = new ArrayList<Estudiante>();
            for (Estudiante listaEstudiantesEstudianteToAttach : grupo.getListaEstudiantes()) {
                listaEstudiantesEstudianteToAttach = em.getReference(listaEstudiantesEstudianteToAttach.getClass(), listaEstudiantesEstudianteToAttach.getId_estudiante());
                attachedListaEstudiantes.add(listaEstudiantesEstudianteToAttach);
            }
            grupo.setListaEstudiantes(attachedListaEstudiantes);
            List<Materia> attachedListaMaterias = new ArrayList<Materia>();
            for (Materia listaMateriasMateriaToAttach : grupo.getListaMaterias()) {
                listaMateriasMateriaToAttach = em.getReference(listaMateriasMateriaToAttach.getClass(), listaMateriasMateriaToAttach.getId_materia());
                attachedListaMaterias.add(listaMateriasMateriaToAttach);
            }
            grupo.setListaMaterias(attachedListaMaterias);
            em.persist(grupo);
            for (Estudiante listaEstudiantesEstudiante : grupo.getListaEstudiantes()) {
                Grupo oldMiGrupoOfListaEstudiantesEstudiante = listaEstudiantesEstudiante.getMiGrupo();
                listaEstudiantesEstudiante.setMiGrupo(grupo);
                listaEstudiantesEstudiante = em.merge(listaEstudiantesEstudiante);
                if (oldMiGrupoOfListaEstudiantesEstudiante != null) {
                    oldMiGrupoOfListaEstudiantesEstudiante.getListaEstudiantes().remove(listaEstudiantesEstudiante);
                    oldMiGrupoOfListaEstudiantesEstudiante = em.merge(oldMiGrupoOfListaEstudiantesEstudiante);
                }
            }
            for (Materia listaMateriasMateria : grupo.getListaMaterias()) {
                listaMateriasMateria.getListaGrupos().add(grupo);
                listaMateriasMateria = em.merge(listaMateriasMateria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getId_grupo());
            List<Estudiante> listaEstudiantesOld = persistentGrupo.getListaEstudiantes();
            List<Estudiante> listaEstudiantesNew = grupo.getListaEstudiantes();
            List<Materia> listaMateriasOld = persistentGrupo.getListaMaterias();
            List<Materia> listaMateriasNew = grupo.getListaMaterias();
            List<Estudiante> attachedListaEstudiantesNew = new ArrayList<Estudiante>();
            for (Estudiante listaEstudiantesNewEstudianteToAttach : listaEstudiantesNew) {
                listaEstudiantesNewEstudianteToAttach = em.getReference(listaEstudiantesNewEstudianteToAttach.getClass(), listaEstudiantesNewEstudianteToAttach.getId_estudiante());
                attachedListaEstudiantesNew.add(listaEstudiantesNewEstudianteToAttach);
            }
            listaEstudiantesNew = attachedListaEstudiantesNew;
            grupo.setListaEstudiantes(listaEstudiantesNew);
            List<Materia> attachedListaMateriasNew = new ArrayList<Materia>();
            for (Materia listaMateriasNewMateriaToAttach : listaMateriasNew) {
                listaMateriasNewMateriaToAttach = em.getReference(listaMateriasNewMateriaToAttach.getClass(), listaMateriasNewMateriaToAttach.getId_materia());
                attachedListaMateriasNew.add(listaMateriasNewMateriaToAttach);
            }
            listaMateriasNew = attachedListaMateriasNew;
            grupo.setListaMaterias(listaMateriasNew);
            grupo = em.merge(grupo);
            for (Estudiante listaEstudiantesOldEstudiante : listaEstudiantesOld) {
                if (!listaEstudiantesNew.contains(listaEstudiantesOldEstudiante)) {
                    listaEstudiantesOldEstudiante.setMiGrupo(null);
                    listaEstudiantesOldEstudiante = em.merge(listaEstudiantesOldEstudiante);
                }
            }
            for (Estudiante listaEstudiantesNewEstudiante : listaEstudiantesNew) {
                if (!listaEstudiantesOld.contains(listaEstudiantesNewEstudiante)) {
                    Grupo oldMiGrupoOfListaEstudiantesNewEstudiante = listaEstudiantesNewEstudiante.getMiGrupo();
                    listaEstudiantesNewEstudiante.setMiGrupo(grupo);
                    listaEstudiantesNewEstudiante = em.merge(listaEstudiantesNewEstudiante);
                    if (oldMiGrupoOfListaEstudiantesNewEstudiante != null && !oldMiGrupoOfListaEstudiantesNewEstudiante.equals(grupo)) {
                        oldMiGrupoOfListaEstudiantesNewEstudiante.getListaEstudiantes().remove(listaEstudiantesNewEstudiante);
                        oldMiGrupoOfListaEstudiantesNewEstudiante = em.merge(oldMiGrupoOfListaEstudiantesNewEstudiante);
                    }
                }
            }
            for (Materia listaMateriasOldMateria : listaMateriasOld) {
                if (!listaMateriasNew.contains(listaMateriasOldMateria)) {
                    listaMateriasOldMateria.getListaGrupos().remove(grupo);
                    listaMateriasOldMateria = em.merge(listaMateriasOldMateria);
                }
            }
            for (Materia listaMateriasNewMateria : listaMateriasNew) {
                if (!listaMateriasOld.contains(listaMateriasNewMateria)) {
                    listaMateriasNewMateria.getListaGrupos().add(grupo);
                    listaMateriasNewMateria = em.merge(listaMateriasNewMateria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupo.getId_grupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getId_grupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> listaEstudiantes = grupo.getListaEstudiantes();
            for (Estudiante listaEstudiantesEstudiante : listaEstudiantes) {
                listaEstudiantesEstudiante.setMiGrupo(null);
                listaEstudiantesEstudiante = em.merge(listaEstudiantesEstudiante);
            }
            List<Materia> listaMaterias = grupo.getListaMaterias();
            for (Materia listaMateriasMateria : listaMaterias) {
                listaMateriasMateria.getListaGrupos().remove(grupo);
                listaMateriasMateria = em.merge(listaMateriasMateria);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
