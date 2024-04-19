<%@page import="java.util.ArrayList"%>
<%@page import="logica.Materia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>

<div class="text-center">
    <h1>Alta Grupos</h1>
    <p>Apartado para añadir grupos al sistema.</p>
</div>
<form accept-charset="UTF-8" class="user" action="SvGrupos" method="POST">
    <div class="form-group row justify-content-center">
        <div class="col-sm-6 mb-2">
            <input type="text" class="form-control form-control-user" name="nombre" placeholder="Nombre">
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <div class="col-sm-6">
            <%                
                List<Materia> listaMaterias = (List) request.getSession().getAttribute("listaMaterias");
                for (Materia materia : listaMaterias) {
            %>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="materiasSeleccionadas[]" id="materia_<%= materia.getId_materia()%>" value="<%= materia.getId_materia()%>">
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
                Dar de Alta Grupo
            </button>
        </div>
    </div>
    <hr>
</form>

<%@include file="components/bodyfinal.jsp" %>
