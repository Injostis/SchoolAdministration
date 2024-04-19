package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Grupo;
import logica.Maestro;
import logica.Trabajo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-10T20:08:22")
@StaticMetamodel(Materia.class)
public class Materia_ { 

    public static volatile ListAttribute<Materia, Maestro> listaMaestros;
    public static volatile ListAttribute<Materia, Trabajo> listaTrabajos;
    public static volatile SingularAttribute<Materia, Long> id_materia;
    public static volatile SingularAttribute<Materia, String> nombre;
    public static volatile ListAttribute<Materia, Grupo> listaGrupos;

}