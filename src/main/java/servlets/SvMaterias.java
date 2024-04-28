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
import logica.Grupo;
import logica.Maestro;
import logica.Materia;
import logica.Trabajo;

@WebServlet(name = "SvMaterias", urlPatterns = {"/SvMaterias"})
public class SvMaterias extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Maestro miMaestro = (Maestro) request.getSession().getAttribute("miMaestro");
        List<Materia> listaMaterias = miMaestro.getListaMaterias();
        HttpSession misesion = request.getSession();
        misesion.setAttribute("listaMaterias", listaMaterias);
        response.sendRedirect("verMaterias.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        List<Grupo> listaGrupos = new ArrayList<>();
        List<Maestro> listaMaestros = new ArrayList<>();
        List<Trabajo> listaTrabajos = new ArrayList<>();
        
        control.crearMateria(nombre, listaGrupos, listaMaestros, listaTrabajos);
        
        response.sendRedirect("SvMaterias");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
