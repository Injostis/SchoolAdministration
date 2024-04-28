<%@page import="java.util.ArrayList"%>
<%@page import="logica.Grupo" %>
<%@page import="logica.Materia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<!DOCTYPE html>
<body id="page-top">

    <!-- Validación Sesión -->
    <%
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect("sinLogin.jsp");
        }
    %>

    <!-- Page Wrapper -->
    <div id="wrapper">

        <%@include file="components/sidebar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <%@include file="components/topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <div class="text-center">
                        <h1>Edición de Grupos</h1>
                        <p>Apartado para editar grupos del sistema.</p>
                    </div>

                    <% Grupo grupo = (Grupo) request.getSession().getAttribute("grupoEditar");%>

                    <form accept-charset="UTF-8" class="user" action="SvEditGrupos" method="POST">
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6 mb-2">
                                <input type="text" class="form-control form-control-user" name="nombre" placeholder="Nombre" value="<%=grupo.getNombre()%>">
                            </div>
                        </div>
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6">
                                <%
                                    List<Materia> listaMaterias = (List) request.getSession().getAttribute("listaMaterias");
                                    List<Materia> materiasAsignadas = grupo.getListaMaterias();
                                    for (Materia materia : listaMaterias) {
                                        boolean marcada = false;
                                        for (Materia materiaAsignada : materiasAsignadas) {
                                            if (materia.getId_materia().equals(materiaAsignada.getId_materia())) {
                                                marcada = true;
                                                break;
                                            }
                                        }
                                %>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="materiasSeleccionadas[]" id="materia_<%= materia.getId_materia()%>" value="<%= materia.getId_materia()%>" <% if (marcada) { %>checked<% }%>>
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
                                    Editar Grupo
                                </button>
                            </div>
                        </div>
                        <hr>
                    </form>

                    <%@include file="components/bodyfinal.jsp" %>
