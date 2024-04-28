<%@page import="java.util.ArrayList"%>
<%@page import="logica.Grupo"%>
<%@page import="logica.Estudiante"%>
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

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-text mx-3">Colegio San Alonso</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-solid fa-bars"></i>
                    <span>Menú</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Gestión
            </div>

            <!-- Nav Item - Materias -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMaterias"
                   aria-expanded="true" aria-controls="collapseMaterias">
                    <i class="fas fa-solid fa-book"></i>
                    <span>Materias</span>
                </a>
                <div id="collapseMaterias" class="collapse" aria-labelledby="headingMaterias" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvMaterias">Ver Materias</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Grupos -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseGrupos"
                   aria-expanded="true" aria-controls="collapseGrupos">
                    <i class="fas fa-solid fa-users"></i>
                    <span>Grupos</span>
                </a>
                <div id="collapseGrupos" class="collapse" aria-labelledby="headingGrupos"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvGrupos">Ver Grupos</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Alumnos -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAlumnos"
                   aria-expanded="true" aria-controls="collapseAlumnos">
                    <i class="fas fa-solid fa-user"></i>
                    <span>Alumnos</span>
                </a>
                <div id="collapseAlumnos" class="collapse" aria-labelledby="headingAlumnos"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvAlumnos">Ver Alumnos</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <%@include file="components/topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid">

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