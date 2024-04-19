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

@WebServlet(name = "SvEditMaestros", urlPatterns = {"/SvEditMaestros"})
public class SvEditMaestros extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Maestro maestro = control.traerMaestro(id);
        
        List<Materia> listaMaterias = control.traerMaterias();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("maestroEditar", maestro);
        misesion.setAttribute("listaMaterias", listaMaterias);
        
        response.sendRedirect("editarMaestros.jsp");
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
        String[] materiasSeleccionadas = request.getParameterValues("materiaSeleccionada[]");
        
        List<Materia> materiasBD = control.traerMaterias();
        
        List<Materia> listaMaterias = new ArrayList<>();
        
        if (materiasSeleccionadas != null) {
             listaMaterias = control.obtenerMateriasSeleccionadas(materiasSeleccionadas, materiasBD);
        }
        
        Maestro maestro = (Maestro) request.getSession().getAttribute("maestroEditar");
        maestro.setNombre(nombre);
        maestro.setApellido_paterno(apellidoPaterno);
        maestro.setApellido_materno(apellidoMaterno);
        maestro.setUsername(username);
        maestro.setPassword(password);
        maestro.setListaMaterias(listaMaterias);
        
        control.editarMaestro(maestro);
        
        response.sendRedirect("SvMaestros");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
