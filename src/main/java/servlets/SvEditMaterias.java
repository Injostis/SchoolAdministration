package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Materia;

@WebServlet(name = "SvEditMaterias", urlPatterns = {"/SvEditMaterias"})
public class SvEditMaterias extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Materia materia = control.traerMateria(id);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("materiaEditar", materia);
        
        response.sendRedirect("editarMaterias.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        
        Materia materia = (Materia) request.getSession().getAttribute("materiaEditar");
        materia.setNombre(nombre);
        
        control.editarMateria(materia);
        
        response.sendRedirect("SvMaterias");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
