package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Estudiante;
import logica.Materia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-10T20:08:22")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, Long> id_grupo;
    public static volatile ListAttribute<Grupo, Estudiante> listaEstudiantes;
    public static volatile ListAttribute<Grupo, Materia> listaMaterias;
    public static volatile SingularAttribute<Grupo, String> nombre;

}