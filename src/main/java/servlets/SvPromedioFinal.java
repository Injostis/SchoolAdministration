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

@WebServlet(name = "SvPromedioFinal", urlPatterns = {"/SvPromedioFinal"})
public class SvPromedioFinal extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Estudiante estudiante = control.traerEstudiante(id);
        List<Calificacion> listaCalificaciones = estudiante.getListaCalificaciones();
        
        float sumCalificaciones = 0f;
        for(Calificacion calificacion : listaCalificaciones) {
            sumCalificaciones += calificacion.getValor();
        }
        
        float promedio = sumCalificaciones / listaCalificaciones.size();
        HttpSession misesion = request.getSession();
        misesion.setAttribute("promedio", promedio);
        misesion.setAttribute("estudiante", estudiante);
        
        response.sendRedirect("verPromedioFinal.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
