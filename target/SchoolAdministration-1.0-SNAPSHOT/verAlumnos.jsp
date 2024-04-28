<%@page import="logica.Materia"%>
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
                        <a class="collapse-item" href="SvAlumnos?accion=altaEstudiantes">Alta Alumnos</a>
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


                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Ver Alumnos</h1>
                        <p class="mb-4">Aquí se puede ver una lista de los Alumnos registrados</p>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Alumnos</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Apellido Paterno</th>
                                                <th>Apellido Materno</th>
                                                <th style="width: 210px">Acción</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Apellido Paterno</th>
                                                <th>Apellido Materno</th>
                                                <th style="width: 210px">Acción</th>
                                            </tr>
                                        </tfoot>

                                        <%
                                            List<Estudiante> listaEstudiantes = (List) request.getSession().getAttribute("listaEstudiantes");
                                        %>

                                        <tbody>
                                            <% for (Estudiante estudiante : listaEstudiantes) {%>
                                            <tr>
                                                <td><%=estudiante.getNombre()%></td>
                                                <td><%=estudiante.getApellido_paterno()%></td>
                                                <td><%=estudiante.getApellido_materno()%></td>
                                                <td style="display: flex; justify-content: space-between; align-items: center; width: 300px;">
                                                    <form name="eliminar" action="SvElimAlumnos" method="POST">
                                                        <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red; margin-right:5px;">
                                                            Eliminar
                                                        </button>
                                                        <input type="hidden" name="id" value="<%=estudiante.getId_estudiante()%>">
                                                    </form>
                                                    <form name="editar" action="SvEditAlumnos" method="GET">
                                                        <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px; margin-right: 5px">
                                                            Editar
                                                        </button>
                                                        <input type="hidden" name="id" value="<%=estudiante.getId_estudiante()%>">
                                                    </form>
                                                    <form name="promedioFinal" action="SvPromedioFinal" method="GET">
                                                        <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left: 5px">
                                                            Promedio
                                                        </button>
                                                        <input type="hidden" name="id" value="<%=estudiante.getId_estudiante()%>">
                                                    </form>
                                                </td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <%@include file="components/bodyfinal.jsp" %>