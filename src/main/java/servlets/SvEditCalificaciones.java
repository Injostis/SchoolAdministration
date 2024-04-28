package servlets;

import java.io.IOException;
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
import logica.Trabajo;

@WebServlet(name = "SvEditCalificaciones", urlPatterns = {"/SvEditCalificaciones"})
public class SvEditCalificaciones extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Long idTrabajo = (Long) request.getSession().getAttribute("idTrabajo");
        
        Estudiante estudiante = control.traerEstudiante(id);

        List<Calificacion> listaCalificaciones = estudiante.getListaCalificaciones();
        Calificacion miCalificacion = new Calificacion();
        boolean existeCalificacion = false;
        for (Calificacion calificacion : listaCalificaciones) {
            if (calificacion.getMiEstudiante().getId_estudiante().equals(id)
                    && calificacion.getMiTrabajo().getId().equals(idTrabajo)) {
                miCalificacion = calificacion;
                existeCalificacion = true;
                break;
            }
        }
        
        if (existeCalificacion) {
            HttpSession misesion = request.getSession();
            misesion.setAttribute("calificacionEditar", miCalificacion);
            response.sendRedirect("editarCalificaciones.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("alerta", "No ha sido calificado");
            response.sendRedirect("calificarAlumnos.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Float valor = Float.parseFloat(request.getParameter("valor"));
        
        Calificacion calificacion = (Calificacion) request.getSession().getAttribute("calificacionEditar");
        calificacion.setValor(valor);
        
        control.editarCalificacion(calificacion);
        
        response.sendRedirect("SvCalificaciones");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
