<%@page import="logica.Maestro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Materia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>

<div class="text-center">
    <h1>Edición de Maestros</h1>
    <p>Apartado para modificar un maestro del sistema.</p>
</div>

<% Maestro maestro = (Maestro) request.getSession().getAttribute("maestroEditar");%>

<form accept-charset="UTF-8" class="user" action="SvEditMaestros" method="POST">
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="text" class="form-control form-control-user" name="nombre" placeholder="Nombre" value="<%=maestro.getNombre()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="text" class="form-control form-control-user" name="apellidoPaterno" placeholder="Apellido Paterno" value="<%=maestro.getApellido_paterno()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="text" class="form-control form-control-user" name="apellidoMaterno" placeholder="Apellido Materno" value="<%=maestro.getApellido_materno()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="text" class="form-control form-control-user" name="username" placeholder="Nombre de Usuario" value="<%=maestro.getUsername()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="password" class="form-control form-control-user" name="password" placeholder="Contraseña" value="<%=maestro.getPassword()%>">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <%
                List<Materia> listaMaterias = (List<Materia>) request.getSession().getAttribute("listaMaterias");
                List<Materia> materiasAsignadas = maestro.getListaMaterias(); // Obtener las materias asignadas al maestro
                for (Materia materia : listaMaterias) {
                    boolean marcada = false; // Flag para indicar si la casilla de verificación debe estar marcada
                    // Verificar si la materia actual está asignada al maestro
                    for (Materia materiaAsignada : materiasAsignadas) {
                        if (materia.getId_materia().equals(materiaAsignada.getId_materia())) {
                            marcada = true; // Marcar la casilla de verificación
                            break;
                        }
                    }
            %>

            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="materiaSeleccionada[]" id="materia_<%= materia.getId_materia()%>" value="<%= materia.getId_materia()%>" <% if (marcada) { %>checked<% }%>>
                <label class="form-check-label" for="materia_<%= materia.getId_materia()%>">
                    <%= materia.getNombre()%>
                </label>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <button class="btn btn-primary btn-user btn-block" type="submit">
                Editar Maestro
            </button>
        </div>
    </div>
    <hr>
</form>

<%@include file="components/bodyfinal.jsp" %>