<%@page import="java.util.ArrayList"%>
<%@page import="logica.Materia"%>
<%@page import="logica.TipoTrabajo"%>
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
                        <h1>Alta Trabajos</h1>
                        <p>Apartado para añadir trabajos al sistema.</p>
                    </div>
                    <form accept-charset="UTF-8" class="user" action="SvTrabajos" method="POST">
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6 mb-2">
                                <input type="text" class="form-control form-control-user" name="nombre" placeholder="Nombre">
                            </div>
                        </div>
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6 mb-2">
                                <textarea class="form-control form-control-user text-center" name="descripcion" placeholder="Descripción" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6">
                                <%        List<TipoTrabajo> listaTipoTrabajos = (List) request.getSession().getAttribute("listaTipoTrabajos");
                                %>
                                <div class="form-group">
                                    <label for="tipoTrabajoSeleccionado">Tipo de Trabajo:</label>
                                    <select class="form-control" id="tipoTrabajoSeleccionado" name="tipoTrabajoSeleccionado">
                                        <option value="">Seleccionar Tipo de Trabajo</option>
                                        <%
                                            for (TipoTrabajo tipoTrabajo : listaTipoTrabajos) {
                                        %>
                                        <option value="<%= tipoTrabajo.getId()%>"><%= tipoTrabajo.getNombreTipo()%></option>
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
                                    Dar de Alta Trabajo
                                </button>
                            </div>
                        </div>
                        <hr>
                    </form>

                    <%@include file="components/bodyfinal.jsp" %>
