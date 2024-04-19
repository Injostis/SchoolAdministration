<%@page import="java.util.ArrayList"%>
<%@page import="logica.Grupo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>

<div class="text-center">
    <h1>Alta Alumnos</h1>
    <p>Apartado para añadir alumnos al sistema.</p>
</div>
<form accept-charset="UTF-8" class="user" action="SvEditAlumnos" method="POST">
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" name="nombre" id="nombre" placeholder="Nombre">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido Paterno">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido Materno">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <%        List<Grupo> listaGrupos = (List) request.getSession().getAttribute("listaGrupos");
            %>
            <div class="form-group">
                <label for="grupoSeleccionado">Grupo:</label>
                <select class="form-control" id="grupoSeleccionado" name="grupoSeleccionado">
                    <option value="">Seleccionar Grupo</option>
                    <%
                        for (Grupo grupo : listaGrupos) {
                    %>
                    <option value="<%= grupo.getId_grupo()%>"><%= grupo.getNombre()%></option>
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
                Dar de Alta Alumno
            </button>
        </div>
    </div>
    <hr>
</form>

<%@include file="components/bodyfinal.jsp" %>