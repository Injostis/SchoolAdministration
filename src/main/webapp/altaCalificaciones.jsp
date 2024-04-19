<%@page import="java.util.ArrayList"%>
<%@page import="logica.Estudiante"%>
<%@page import="logica.Trabajo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>

<div class="text-center">
    <h1>Alta Calificaciones</h1>
    <p>Apartado para añadir calificaciones al sistema.</p>
</div>
<form accept-charset="UTF-8" class="user" action="SvCalificaciones" method="POST">
    <%        List<Estudiante> listaEstudiantes = (List) request.getSession().getAttribute("listaEstudiantes");
    %>
    <div class="form-group">
        <label for="estudianteSeleccionado">Alumno:</label>
        <select class="form-control" id="estudianteSeleccionado" name="estudianteSeleccionado">
            <option value="">Seleccionar Estudiante</option>
            <%
                for (Estudiante estudiante : listaEstudiantes) {
            %>
            <option value="<%= estudiante.getId_estudiante()%>"><%= estudiante.getNombre()%></option>
            <%
                }
            %>
        </select>
    </div>
    <%        List<Trabajo> listaTrabajos = (List) request.getSession().getAttribute("listaTrabajos");
    %>
    <div class="form-group">
        <label for="trabajoSeleccionado">Trabajo:</label>
        <select class="form-control" id="trabajoSeleccionado" name="trabajoSeleccionado">
            <option value="">Seleccionar Trabajo</option>
            <%
                for (Trabajo trabajo : listaTrabajos) {
            %>
            <option value="<%= trabajo.getId()%>"><%= trabajo.getNombre()%></option>
            <%
                }
            %>
        </select>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="number" class="form-control form-control-user" name="valor" id="valorInput" placeholder="Valor" min="0" max="100" required>
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <button class="btn btn-primary btn-user btn-block" type="submit">
                Dar de Alta una Calificacion
            </button>
        </div>
    </div>
    <hr>
</form>

<%@include file="components/bodyfinal.jsp" %>
