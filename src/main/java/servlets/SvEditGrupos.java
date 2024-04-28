package servlets;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Grupo;
import logica.Materia;

@WebServlet(name = "SvEditGrupos", urlPatterns = {"/SvEditGrupos"})
public class SvEditGrupos extends HttpServlet {
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Grupo grupo = control.traerGrupo(id);
        List<Materia> listaMaterias = control.traerMaterias();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("grupoEditar", grupo);
        misesion.setAttribute("listaMaterias", listaMaterias);
        
        response.sendRedirect("editarGrupos.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        String[] materiasSeleccionadas = request.getParameterValues("materiasSeleccionadas[]");
        
        List<Materia> materiasBD = control.traerMaterias();
        
        List<Materia> listaMaterias = new ArrayList<>();
        
        if (materiasSeleccionadas != null) {
            listaMaterias = control.obtenerMateriasSeleccionadas(materiasSeleccionadas, materiasBD);
        }
        
        Grupo grupo = (Grupo) request.getSession().getAttribute("grupoEditar");
        grupo.setNombre(nombre);
        grupo.setListaMaterias(listaMaterias);
        
        control.editarGrupo(grupo);
        
        response.sendRedirect("SvGrupos");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
