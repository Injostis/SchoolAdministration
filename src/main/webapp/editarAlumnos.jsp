<%@page import="java.util.ArrayList"%>
<%@page import="logica.Grupo"%>
<%@page import="logica.Estudiante"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>

<div class="text-center">
    <h1>Editar Alumnos</h1>
    <p>Apartado para editar alumnos del sistema.</p>
</div>

<% Estudiante estudiante = (Estudiante) request.getSession().getAttribute("estudianteEditar");%>

<form accept-charset="UTF-8" class="user" action="SvEditAlumnos" method="POST">
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" name="nombre" id="nombre" placeholder="Nombre" value="<%=estudiante.getNombre()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido Paterno" value="<%=estudiante.getApellido_paterno()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido Materno" value="<%=estudiante.getApellido_materno()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <%
                List<Grupo> listaGrupos = (List) request.getSession().getAttribute("listaGrupos");
                Long idGrupoAlumno = estudiante.getMiGrupo().getId_grupo();
            %>
            <div class="form-group">
                <label for="grupoSeleccionado">Grupo:</label>
                <select class="form-control" id="grupoSeleccionado" name="grupoSeleccionado">
                    <option value="">Seleccionar Grupo</option>
                    <% for (Grupo grupo : listaGrupos) {
                    %>
                    <option value="<%= grupo.getId_grupo()%>" <%= grupo.getId_grupo() == idGrupoAlumno ? "selected" : ""%>><%= grupo.getNombre()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <button class="btn btn-primary btn-user btn-block" type="submit">
                Editar Alumno
            </button>
        </div>
    </div>
    <hr>
</form>

<%@include file="components/bodyfinal.jsp" %>