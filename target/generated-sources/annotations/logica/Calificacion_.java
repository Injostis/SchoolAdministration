package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Estudiante;
import logica.Trabajo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-10T20:08:22")
@StaticMetamodel(Calificacion.class)
public class Calificacion_ { 

    public static volatile SingularAttribute<Calificacion, Long> id_calificacion;
    public static volatile SingularAttribute<Calificacion, Float> valor;
    public static volatile SingularAttribute<Calificacion, Estudiante> miEstudiante;
    public static volatile SingularAttribute<Calificacion, Trabajo> miTrabajo;

}