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

@WebServlet(name = "SvAlumnos", urlPatterns = {"/SvAlumnos"})
public class SvAlumnos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String accion = request.getParameter("accion");
        if ("altaEstudiantes".equals(accion)) {
            List<Grupo> listaGrupos = control.traerGrupos();
            misesion.setAttribute("listaGrupos", listaGrupos);
            response.sendRedirect("altaAlumnos.jsp");
        } else if("calificarAlumnos".equals(accion)){
            Long id = Long.parseLong(request.getParameter("id"));
            misesion.setAttribute("idTrabajo", id);
            Grupo miGrupo = control.traerGrupo((Long) request.getSession().getAttribute("idGrupo"));
            List<Estudiante> listaEstudiantes = miGrupo.getListaEstudiantes();
            misesion.setAttribute("listaEstudiantes", listaEstudiantes);
            response.sendRedirect("calificarAlumnos.jsp");
        } else {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                misesion.setAttribute("idGrupo", id);
                Grupo miGrupo = control.traerGrupo(id);
                List<Estudiante> listaEstudiantes = miGrupo.getListaEstudiantes();
                misesion.setAttribute("listaEstudiantes", listaEstudiantes);
                response.sendRedirect("verAlumnos.jsp");
            } catch (NumberFormatException e) {
                Grupo miGrupo = control.traerGrupo((Long) request.getSession().getAttribute("idGrupo"));
                List<Estudiante> listaEstudiantes = miGrupo.getListaEstudiantes();
                misesion.setAttribute("listaEstudiantes", listaEstudiantes);
                response.sendRedirect("verAlumnos.jsp");
            }
        }
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

        if (grupoSeleccionado != null) {
            miGrupo = control.obtenerGrupoSeleccionado(grupoSeleccionado, gruposBD);
        }

        control.crearEstudiante(nombre, apellidoPaterno, apellidoMaterno, miGrupo, listaCalificaciones);

        response.sendRedirect("SvAlumnos");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
