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
import logica.Maestro;
import logica.Materia;

@WebServlet(name = "SvMaestros", urlPatterns = {"/SvMaestros"})
public class SvMaestros extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if ("altaMaestros".equals(accion)) {
            List<Materia> listaMaterias = control.traerMaterias();
            HttpSession misesion = request.getSession();
            misesion.setAttribute("listaMaterias", listaMaterias);
            response.sendRedirect("altaMaestros.jsp");
        } else {
            List<Maestro> listaMaestros = control.traerMaestros();
            HttpSession misesion = request.getSession();
            misesion.setAttribute("listaMaestros", listaMaestros);
            response.sendRedirect("verMaestros.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] materiasSeleccionadas = request.getParameterValues("materiasSeleccionadas[]");
        
        List<Materia> materiasBD = control.traerMaterias();
        
        List<Materia> listaMaterias = new ArrayList<>();
        
        if (materiasSeleccionadas != null) {
             listaMaterias = control.obtenerMateriasSeleccionadas(materiasSeleccionadas, materiasBD);
        }                  
      
        control.crearMaestro(nombre, apellidoPaterno, apellidoMaterno, username, password, listaMaterias);

        response.sendRedirect("SvMaestros");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
