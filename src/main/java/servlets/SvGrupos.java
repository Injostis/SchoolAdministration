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
import logica.Controladora;
import logica.Estudiante;
import logica.Grupo;
import logica.Materia;

@WebServlet(name = "SvGrupos", urlPatterns = {"/SvGrupos"})
public class SvGrupos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {           
        HttpSession misesion = request.getSession();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            misesion.setAttribute("idMateria", id);
            Materia miMateria = control.traerMateria(id);
            List<Grupo> listaGrupos = miMateria.getListaGrupos();
            misesion.setAttribute("listaGrupos", listaGrupos);
            response.sendRedirect("verGrupos.jsp");
        } catch (NumberFormatException e) {
            Materia miMateria = control.traerMateria((Long) request.getSession().getAttribute("idMateria"));
            List<Grupo> listaGrupos = miMateria.getListaGrupos();
            misesion.setAttribute("listaGrupos", listaGrupos);
            response.sendRedirect("verGrupos.jsp");
        }     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        String[] materiasSeleccionadas = request.getParameterValues("materiasSeleccionadas[]");
        
        List<Materia> materiasBD = control.traerMaterias();
        
        List<Materia> listaMaterias = new ArrayList<>();
        
        if (materiasSeleccionadas != null) {
             listaMaterias = control.obtenerMateriasSeleccionadas(materiasSeleccionadas, materiasBD);
        } 

        control.crearGrupo(nombre, listaEstudiantes, listaMaterias);

        response.sendRedirect("SvGrupos");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
