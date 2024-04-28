<%@page import="java.util.ArrayList"%>
<%@page import="logica.Trabajo"%>
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
                        <h1>Editar Trabajos</h1>
                        <p>Apartado para editar trabajos del sistema.</p>
                    </div>

                    <% Trabajo trabajo = (Trabajo) request.getSession().getAttribute("trabajoEditar");%>

                    <form accept-charset="UTF-8" class="user" action="SvEditTrabajos" method="POST">
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6 mb-2">
                                <input type="text" class="form-control form-control-user" name="nombre" placeholder="Nombre" value="<%=trabajo.getNombre()%>">
                            </div>
                        </div>
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6 mb-2">
                                <textarea class="form-control form-control-user text-center" name="descripcion" placeholder="Descripción" style="height: 150px;"><%=trabajo.getDescripcion()%></textarea>
                            </div>
                        </div>
                        <%
                            List<TipoTrabajo> listaTipoTrabajos = (List) request.getSession().getAttribute("listaTipoTrabajos");
                            Long idTipoTrabajo = trabajo.getMiTipoTrabajo().getId();
                        %>
                        <div class="form-group">
                            <label for="tipoTrabajoSeleccionado">Tipo de Trabajo:</label>
                            <select class="form-control" id="tipoTrabajoSeleccionado" name="tipoTrabajoSeleccionado">
                                <option value="">Seleccionar Tipo de Trabajo</option>
                                <%
                                    for (TipoTrabajo tipoTrabajo : listaTipoTrabajos) {
                                %>
                                <option value="<%= tipoTrabajo.getId()%>" <%=tipoTrabajo.getId() == idTipoTrabajo ? "selected" : ""%>><%= tipoTrabajo.getNombreTipo()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <%
                            List<Materia> listaMaterias = (List) request.getSession().getAttribute("listaMaterias");
                            Long idMateria = trabajo.getMiMateria().getId_materia();
                        %>
                        <div class="form-group">
                            <label for="materiaSeleccionada">Materia:</label>
                            <select class="form-control" id="materiaSeleccionada" name="materiaSeleccionada">
                                <option value="">Seleccionar Materia</option>
                                <%
                                    for (Materia materia : listaMaterias) {
                                %>
                                <option value="<%= materia.getId_materia()%>" <%=materia.getId_materia() == idMateria ? "selected" : ""%>><%= materia.getNombre()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6">
                                <button class="btn btn-primary btn-user btn-block" type="submit">
                                    Editar Trabajo
                                </button>
                            </div>
                        </div>
                        <hr>
                    </form>

                    <%@include file="components/bodyfinal.jsp" %>
