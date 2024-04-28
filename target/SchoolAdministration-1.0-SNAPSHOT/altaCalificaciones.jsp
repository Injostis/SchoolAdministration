<%@page import="java.util.ArrayList"%>
<%@page import="logica.Estudiante"%>
<%@page import="logica.Materia"%>
<%@page import="logica.Trabajo"%>
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

            <!-- Nav Item - Trabajos -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTrabajos"
                   aria-expanded="true" aria-controls="collapseTrabajos">
                    <i class="fas fa-solid fa-briefcase"></i>
                    <span>Trabajos</span>
                </a>
                <div id="collapseTrabajos" class="collapse" aria-labelledby="headingTrabajos"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvTrabajos?accion=altaTrabajos">Alta Trabajos</a>
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
                        <h1>Calificar</h1>
                        <p>Apartado para calificar un trabajo.</p>
                    </div>
                    <form accept-charset="UTF-8" class="user" action="SvCalificaciones" method="POST">
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6 mb-2">
                                <input type="number" class="form-control form-control-user" name="valor" id="valorInput" placeholder="Valor" min="0" max="100" required>
                            </div>
                        </div>
                        <div class="form-group row justify-content-center">
                            <div class="col-sm-6">
                                <button class="btn btn-primary btn-user btn-block" type="submit">
                                    Calificar
                                </button>
                            </div>
                        </div>
                        <hr>
                    </form>

                    <%@include file="components/bodyfinal.jsp" %>
