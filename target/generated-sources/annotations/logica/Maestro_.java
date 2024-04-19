package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Materia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-10T20:08:22")
@StaticMetamodel(Maestro.class)
public class Maestro_ { 

    public static volatile SingularAttribute<Maestro, String> password;
    public static volatile ListAttribute<Maestro, Materia> listaMaterias;
    public static volatile SingularAttribute<Maestro, Long> id_maestro;
    public static volatile SingularAttribute<Maestro, String> apellido_paterno;
    public static volatile SingularAttribute<Maestro, String> apellido_materno;
    public static volatile SingularAttribute<Maestro, String> nombre;
    public static volatile SingularAttribute<Maestro, String> username;

}