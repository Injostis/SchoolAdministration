package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Materia;
import logica.TipoTrabajo;
import logica.Trabajo;

@WebServlet(name = "SvEditTrabajos", urlPatterns = {"/SvEditTrabajos"})
public class SvEditTrabajos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Trabajo trabajo = control.traerTrabajo(id);
        List<TipoTrabajo> listaTipoTrabajos = control.traerTipoTrabajos();
        List<Materia> listaMaterias = control.traerMaterias();

        HttpSession misesion = request.getSession();
        misesion.setAttribute("trabajoEditar", trabajo);
        misesion.setAttribute("listaTipoTrabajos", listaTipoTrabajos);
        misesion.setAttribute("listaMaterias", listaMaterias);
        
        response.sendRedirect("editarTrabajos.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tipoTrabajoSeleccionado = request.getParameter("tipoTrabajoSeleccionado");
        String materiaSeleccionada = request.getParameter("materiaSeleccionada");
        
        List<TipoTrabajo> tipoTrabajosBD = control.traerTipoTrabajos();
        List<Materia> materiasBD = control.traerMaterias();
        
        TipoTrabajo miTipoTrabajo = new TipoTrabajo();
        Materia miMateria = new Materia();

        if(tipoTrabajoSeleccionado != null) {
            miTipoTrabajo = control.obtenerTipoTrabajoSeleccionado(tipoTrabajoSeleccionado, tipoTrabajosBD);
        }
        
        if(materiaSeleccionada != null) {
            miMateria = control.obtenerMateriaSeleccionada(materiaSeleccionada, materiasBD);
        }
        
        Trabajo trabajo = (Trabajo) request.getSession().getAttribute("trabajoEditar");
        trabajo.setNombre(nombre);
        trabajo.setDescripcion(descripcion);
        trabajo.setMiTipoTrabajo(miTipoTrabajo);
        trabajo.setMiMateria(miMateria);
        
        control.editarTrabajo(trabajo);
        
        response.sendRedirect("SvTrabajos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
