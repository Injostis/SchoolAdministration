package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Calificacion;
import logica.Controladora;
import logica.Estudiante;
import logica.Grupo;

@WebServlet(name = "SvEditAlumnos", urlPatterns = {"/SvEditAlumnos"})
public class SvEditAlumnos extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Estudiante estudiante = control.traerEstudiante(id);
        List<Grupo> listaGrupos = control.traerGrupos();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("estudianteEditar", estudiante);
        misesion.setAttribute("listaGrupos", listaGrupos);
        
        response.sendRedirect("editarAlumnos.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String grupoSeleccionado = request.getParameter("grupoSeleccionado");
        List<Calificacion> listaCalificaciones = new ArrayList<>();
        
        List<Grupo> gruposBD = control.traerGrupos();
        
        Grupo miGrupo = new Grupo();
        
        if(grupoSeleccionado != null) {
            miGrupo = control.obtenerGrupoSeleccionado(grupoSeleccionado, gruposBD);
        }
        
        Estudiante estudiante = (Estudiante) request.getSession().getAttribute("estudianteEditar");
        estudiante.setNombre(nombre);
        estudiante.setApellido_paterno(apellidoPaterno);
        estudiante.setApellido_materno(apellidoMaterno);
        estudiante.setMiGrupo(miGrupo);
        estudiante.setListaCalificaciones(listaCalificaciones);
        
        control.editarEstudiante(estudiante);
        
        response.sendRedirect("SvAlumnos");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
