<%@page import="logica.Materia"%>
<%@page import="logica.Maestro"%>
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


                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Ver Maestros</h1>
                        <p class="mb-4">Aquí se puede ver una lista de los Maestros registrados</p>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Maestros</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Apellido Paterno</th>
                                                <th>Apellido Materno</th>
                                                <th>Materias</th>
                                                <th style="width: 210px">Acción</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Apellido Paterno</th>
                                                <th>Apellido Materno</th>
                                                <th>Materias</th>
                                                <th style="width: 210px">Acción</th>
                                            </tr>
                                        </tfoot>

                                        <%
                                            List<Maestro> listaMaestros = (List) request.getSession().getAttribute("listaMaestros");
                                        %>

                                        <tbody>
                                            <% for (Maestro maestro : listaMaestros) {%>
                                            <tr>
                                                <td><%=maestro.getNombre()%></td>
                                                <td><%=maestro.getApellido_paterno()%></td>
                                                <td><%=maestro.getApellido_materno()%></td>
                                                <td>
                                                    <%
                                                        List<Materia> listaMaterias = maestro.getListaMaterias();
                                                        StringBuilder materiasStr = new StringBuilder();
                                                        for (int i = 0; i < listaMaterias.size(); i++) {
                                                            Materia materia = listaMaterias.get(i);
                                                            materiasStr.append(materia.getNombre());
                                                            if (i < listaMaterias.size() - 1) {
                                                                materiasStr.append(", ");
                                                            }
                                                        }
                                                        out.println(materiasStr);
                                                    %>
                                                </td>
                                                <td style="display: flex; width: 230px;">
                                                    <form name="eliminar" action="SvElimMaestros" method="POST">
                                                        <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red; margin-right:5px;">
                                                            <i class="fas fa-trash-alt"></i> Eliminar
                                                        </button>
                                                        <input type="hidden" name="id" value="<%=maestro.getId_maestro()%>">
                                                    </form>
                                                    <form name="editar" action="SvEditMaestros" method="GET">
                                                        <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px;">
                                                            <i class="fas fa-pencil-alt"></i> Editar
                                                        </button>
                                                        <input type="hidden" name="id" value="<%=maestro.getId_maestro()%>">
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