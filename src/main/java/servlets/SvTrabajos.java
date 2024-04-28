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
import logica.Materia;
import logica.TipoTrabajo;
import logica.Trabajo;

@WebServlet(name = "SvTrabajos", urlPatterns = {"/SvTrabajos"})
public class SvTrabajos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String accion = request.getParameter("accion");
        if ("altaTrabajos".equals(accion)) {
            List<TipoTrabajo> listaTipoTrabajos = control.traerTipoTrabajos();
            misesion.setAttribute("listaTipoTrabajos", listaTipoTrabajos);
            response.sendRedirect("altaTrabajos.jsp");
        } else {
            Materia miMateria = control.traerMateria((Long) request.getSession().getAttribute("idMateria"));
            List<Trabajo> listaTrabajos = miMateria.getListaTrabajos();
            misesion.setAttribute("listaTrabajos", listaTrabajos);
            response.sendRedirect("verTrabajos.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tipoTrabajoSeleccionado = request.getParameter("tipoTrabajoSeleccionado");
        List<Calificacion> listaCalificaciones = new ArrayList<>();

        List<TipoTrabajo> tipoTrabajosBD = control.traerTipoTrabajos();

        TipoTrabajo miTipoTrabajo = new TipoTrabajo();
        Materia miMateria = control.traerMateria((Long) request.getSession().getAttribute("idMateria"));

        if (tipoTrabajoSeleccionado != null) {
            miTipoTrabajo = control.obtenerTipoTrabajoSeleccionado(tipoTrabajoSeleccionado, tipoTrabajosBD);
        }

        control.crearTrabajo(nombre, descripcion, miTipoTrabajo, miMateria, listaCalificaciones);

        response.sendRedirect("SvTrabajos");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
