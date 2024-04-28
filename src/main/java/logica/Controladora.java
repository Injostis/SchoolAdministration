package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    //CREATE
    public void crearMaestro(
            String nombre,
            String apellido_paterno,
            String apellido_materno,
            String username,
            String password,
            List<Materia> listaMaterias) {
        Maestro maestro = new Maestro();
        maestro.setNombre(nombre);
        maestro.setApellido_paterno(apellido_paterno);
        maestro.setApellido_materno(apellido_materno);
        maestro.setUsername(username);
        maestro.setPassword(password);
        maestro.setListaMaterias(listaMaterias);
        controlPersis.crearMaestro(maestro);
    }

    public void crearEstudiante(
            String nombre,
            String apellido_paterno,
            String apellido_materno,
            Grupo miGrupo,
            List<Calificacion> listaCalificaciones) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido_paterno(apellido_paterno);
        estudiante.setApellido_materno(apellido_materno);
        estudiante.setMiGrupo(miGrupo);
        estudiante.setListaCalificaciones(listaCalificaciones);
        controlPersis.crearEstudiante(estudiante);
    }

    public void crearGrupo(
            String nombre,
            List<Estudiante> listaEstudiantes,
            List<Materia> listaMaterias) {
        Grupo grupo = new Grupo();
        grupo.setNombre(nombre);
        grupo.setListaEstudiantes(listaEstudiantes);
        grupo.setListaMaterias(listaMaterias);
        controlPersis.crearGrupo(grupo);
    }

    public void crearMateria(
            String nombre,
            List<Grupo> listaGrupos,
            List<Maestro> listaMaestros,
            List<Trabajo> listaTrabajos) {
        Materia materia = new Materia();
        materia.setNombre(nombre);
        materia.setListaGrupos(listaGrupos);
        materia.setListaMaestros(listaMaestros);
        materia.setListaTrabajos(listaTrabajos);
        controlPersis.crearMateria(materia);
    }

    public void crearTrabajo(
            String nombre,
            String descripcion,
            TipoTrabajo miTipoTrabajo,
            Materia miMateria,
            List<Calificacion> listaCalificaciones) {
        Trabajo trabajo = new Trabajo();
        trabajo.setNombre(nombre);
        trabajo.setDescripcion(descripcion);
        trabajo.setMiTipoTrabajo(miTipoTrabajo);
        trabajo.setMiMateria(miMateria);
        trabajo.setListaCalificaciones(listaCalificaciones);
        controlPersis.crearTrabajo(trabajo);
    }

    public void crearTipoTrabajo(String nombreTipo) {
        TipoTrabajo tipoTrabajo = new TipoTrabajo();
        tipoTrabajo.setNombreTipo(nombreTipo);
        controlPersis.crearTipoTrabajo(tipoTrabajo);
    }

    public void crearCalifiacion(
            float valor,
            Estudiante miEstudiante,
            Trabajo miTrabajo) {
        Calificacion calificacion = new Calificacion();
        calificacion.setValor(valor);
        calificacion.setMiEstudiante(miEstudiante);
        calificacion.setMiTrabajo(miTrabajo);
        controlPersis.crearCalificacion(calificacion);
    }

    //READ
    public List<Maestro> traerMaestros() {
        return controlPersis.traerMaestros();
    }

    public List<Estudiante> traerEstudiantes() {
        return controlPersis.traerEstudiantes();
    }

    public List<Grupo> traerGrupos() {
        return controlPersis.traerGrupos();
    }

    public List<Materia> traerMaterias() {
        return controlPersis.traerMaterias();
    }

    public List<Trabajo> traerTrabajos() {
        return controlPersis.traerTrabajos();
    }

    public List<TipoTrabajo> traerTipoTrabajos() {
        return controlPersis.traerTipoTrabajos();
    }

    public List<Calificacion> traerCalificaciones() {
        return controlPersis.traerCalificaciones();
    }

    //UPDATE
    public Maestro traerMaestro(Long id) {
        return controlPersis.traerMaestro(id);
    }

    public void editarMaestro(Maestro maestro) {
        controlPersis.editarMaestro(maestro);
    }
    
    public Estudiante traerEstudiante(Long id) {
        return controlPersis.traerEstudiante(id);
    }
    
    public void editarEstudiante(Estudiante estudiante) {
        controlPersis.editarEstudiante(estudiante);
    }
    
    public Grupo traerGrupo(Long id) {
        return controlPersis.traerGrupo(id);
    }
    
    public void editarGrupo(Grupo grupo) {
        controlPersis.editarGrupo(grupo);
    }
    
    public Materia traerMateria(Long id) {
        return controlPersis.traerMateria(id);
    }
    
    public void editarMateria(Materia materia) {
        controlPersis.editarMateria(materia);
    }
    
    public Trabajo traerTrabajo(Long id) {
        return controlPersis.traerTrabajo(id);
    }
    
    public void editarTrabajo(Trabajo trabajo) {
        controlPersis.editarTrabajo(trabajo);
    }
    
    public TipoTrabajo traerTipoTrabajo(Long id) {
        return controlPersis.traerTipoTrabajo(id);
    }
    
    public void editarTipoTrabajo(TipoTrabajo tipoTrabajo) {
        controlPersis.editarTipoTrabajo(tipoTrabajo);
    }
    
    public Calificacion traerCalificacion(Long id) {
        return controlPersis.traerCalificacion(id);
    }
    
    public void editarCalificacion(Calificacion calificacion) {
        controlPersis.editarCalificacion(calificacion);
    }

    //DELETE
    public void borrarMaestro(Long id) {
        controlPersis.borrarMaestro(id);
    }

    public void borrarEstudiante(Long id) {
        controlPersis.borrarEstudiante(id);
    }

    public void borrarGrupo(Long id) {
        controlPersis.borrarGrupo(id);
    }

    public void borrarMateria(Long id) {
        controlPersis.borrarMateria(id);
    }

    public void borrarTrabajo(Long id) {
        controlPersis.borrarTrabajo(id);
    }

    public void borrarTipoTrabajo(Long id) {
        controlPersis.borrarTipoTrabajo(id);
    }

    public void borrarCalificacion(Long id) {
        controlPersis.borrarCalificacion(id);
    }
    
    public Optional<Maestro> comprobarIngreso(String username, String password) {
        List<Maestro> listaMaestros = new ArrayList<>();
        listaMaestros = controlPersis.traerMaestros();
        
        for(Maestro maestro : listaMaestros) {
            if (maestro.getUsername().equals(username)) {
                if(maestro.getPassword().equals(password)) {
                    return Optional.of(maestro);
                    
                }
            }
        }
        return Optional.empty();
    }

    public List<Materia> obtenerMateriasSeleccionadas(String[] idsSeleccionados, List<Materia> listaMaterias) {
    // Convert the IDs from String to int
    List<Long> idsSeleccionadosLong = new ArrayList<>();
    for (String idString : idsSeleccionados) {
        try {
            idsSeleccionadosLong.add(Long.parseLong(idString));
        } catch (NumberFormatException e) {
            // Log the error for debugging
            System.err.println("Error parsing materia ID: " + idString);
            // Consider returning an empty list or throwing a custom exception
        }
    }

    // Create a map for efficient lookup by ID
    Map<Long, Materia> materiasMap = new HashMap<>();
    for (Materia materia : listaMaterias) {
        materiasMap.put(materia.getId_materia(), materia);
    }

    List<Materia> materiasEncontradas = new ArrayList<>();
    for (Long idSeleccionado : idsSeleccionadosLong) {
        Materia materia = materiasMap.get(idSeleccionado);
        if (materia != null) {
            materiasEncontradas.add(materia);
        }
    }
    return materiasEncontradas;
}

    public Grupo obtenerGrupoSeleccionado(String grupoSeleccionado, List<Grupo> gruposBD) {
        Long idGrupoSeleccionado = Long.parseLong(grupoSeleccionado);
        for(Grupo grupo : gruposBD) {
            if(grupo.getId_grupo().equals(idGrupoSeleccionado)) {
                return grupo;
            }
        }
        return null;
    }
    
    public TipoTrabajo obtenerTipoTrabajoSeleccionado(String tipoTrabajoSeleccionado, List<TipoTrabajo> tipoTrabajosBD) {
        Long idTipoTrabajoSeleccionado = Long.parseLong(tipoTrabajoSeleccionado);
        for(TipoTrabajo tipoTrabajo : tipoTrabajosBD) {
            if(tipoTrabajo.getId().equals(idTipoTrabajoSeleccionado)) {
                return tipoTrabajo;
            }
        }
        return null;
    }
    
    public Materia obtenerMateriaSeleccionada(String materiaSeleccionada, List<Materia> materiasBD) {
        Long idMateriaSeleccionada = Long.parseLong(materiaSeleccionada);
        for(Materia materia : materiasBD) {
            if(materia.getId_materia().equals(idMateriaSeleccionada)) {
                return materia;
            }
        }
        return null;
    }
    
    public Trabajo obtenerTrabajoSeleccionado(String trabajoSeleccionado, List<Trabajo> trabajosBD) {
        Long idTrabajoSeleccionado = Long.parseLong(trabajoSeleccionado);
        for(Trabajo trabajo : trabajosBD) {
            if(trabajo.getId().equals(idTrabajoSeleccionado)) {
                return trabajo;
            }
        }
        return null;
    }
    
    public Estudiante obtenerEstudianteSeleccionado(String estudianteSeleccionado, List<Estudiante> estudiantesBD) {
        Long idEstudianteSeleccionado = Long.parseLong(estudianteSeleccionado);
        for(Estudiante estudiante : estudiantesBD) {
            if(estudiante.getId_estudiante().equals(idEstudianteSeleccionado)) {
                return estudiante;
            }
        }
        return null;
    }
    
    public boolean existeCalificacion(Estudiante estudiante, Trabajo trabajo){
        
        return false;
    }
}
