package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Materia implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;
    
    private String nombre;
    
    @ManyToMany
    @JoinTable(name = "materia_grupo",
            joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> listaGrupos;
    
    @ManyToMany
    @JoinTable(name = "materia_maestro",
            joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_maestro"))
    private List<Maestro> listaMaestros;

    @OneToMany(mappedBy = "miMateria")
    private List<Trabajo> listaTrabajos;
    
    public Materia() {
    }

    public Materia(Long id_materia, String nombre, List<Grupo> listaGrupos, List<Maestro> listaMaestros, List<Trabajo> listaTrabajos) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.listaGrupos = listaGrupos;
        this.listaMaestros = listaMaestros;
        this.listaTrabajos = listaTrabajos;
    }

    public Long getId_materia() {
        return id_materia;
    }

    public void setId_materia(Long id_materia) {
        this.id_materia = id_materia;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<Maestro> getListaMaestros() {
        return listaMaestros;
    }

    public void setListaMaestros(List<Maestro> listaMaestros) {
        this.listaMaestros = listaMaestros;
    }

    public List<Trabajo> getListaTrabajos() {
        return listaTrabajos;
    }

    public void setListaTrabajos(List<Trabajo> listaTrabajos) {
        this.listaTrabajos = listaTrabajos;
    }
}
