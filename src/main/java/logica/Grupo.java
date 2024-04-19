package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Grupo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_grupo;
    
    private String nombre;
    
    @OneToMany(mappedBy = "miGrupo")
    private List<Estudiante> listaEstudiantes;
    
    @ManyToMany(mappedBy = "listaGrupos")
    private List<Materia> listaMaterias;

    public Grupo() {
    }

    public Grupo(Long id_grupo, String nombre, List<Estudiante> listaEstudiantes, List<Materia> listaMaterias) {
        this.id_grupo = id_grupo;
        this.nombre = nombre;
        this.listaEstudiantes = listaEstudiantes;
        this.listaMaterias = listaMaterias;
    }

    public Long getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Long id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
     
}
