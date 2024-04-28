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
import logica.Materia;
import logica.Trabajo;

@WebServlet(name = "SvCalificaciones", urlPatterns = {"/SvCalificaciones"})
public class SvCalificaciones extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String accion = request.getParameter("accion");
        if ("calificarAlumno".equals(accion)) {
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

            if (!existeCalificacion) {
                misesion.setAttribute("idEstudiante", id);
                response.sendRedirect("altaCalificaciones.jsp");
            } else {
                misesion.setAttribute("alerta", "Ya fue calificado");
                response.sendRedirect("calificarAlumnos.jsp");
            }

        } else {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                misesion.setAttribute("idGrupo", id);
                Materia miMateria = control.traerMateria((Long) request.getSession().getAttribute("idMateria"));
                List<Trabajo> listaTrabajos = miMateria.getListaTrabajos();
                misesion.setAttribute("listaTrabajos", listaTrabajos);
                response.sendRedirect("verCalificaciones.jsp");
            } catch (NumberFormatException e) {
                Materia miMateria = control.traerMateria((Long) request.getSession().getAttribute("idMateria"));
                List<Trabajo> listaTrabajos = miMateria.getListaTrabajos();
                misesion.setAttribute("listaTrabajos", listaTrabajos);
                response.sendRedirect("verCalificaciones.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Float valor = Float.parseFloat(request.getParameter("valor"));

        Estudiante miEstudiante = control.traerEstudiante((Long) request.getSession().getAttribute("idEstudiante"));
        Trabajo miTrabajo = control.traerTrabajo((Long) request.getSession().getAttribute("idTrabajo"));

        control.crearCalifiacion(valor, miEstudiante, miTrabajo);

        response.sendRedirect("SvCalificaciones");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
