package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Calificacion;
import logica.Grupo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-10T20:08:22")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile ListAttribute<Estudiante, Calificacion> listaCalificaciones;
    public static volatile SingularAttribute<Estudiante, Long> id_estudiante;
    public static volatile SingularAttribute<Estudiante, String> apellido_paterno;
    public static volatile SingularAttribute<Estudiante, String> apellido_materno;
    public static volatile SingularAttribute<Estudiante, String> nombre;
    public static volatile SingularAttribute<Estudiante, Grupo> miGrupo;

}