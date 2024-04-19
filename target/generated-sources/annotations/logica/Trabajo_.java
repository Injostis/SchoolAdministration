package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Calificacion;
import logica.Materia;
import logica.TipoTrabajo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-10T20:08:22")
@StaticMetamodel(Trabajo.class)
public class Trabajo_ { 

    public static volatile SingularAttribute<Trabajo, String> descripcion;
    public static volatile ListAttribute<Trabajo, Calificacion> listaCalificaciones;
    public static volatile SingularAttribute<Trabajo, Long> id;
    public static volatile SingularAttribute<Trabajo, TipoTrabajo> miTipoTrabajo;
    public static volatile SingularAttribute<Trabajo, String> nombre;
    public static volatile SingularAttribute<Trabajo, Materia> miMateria;

}