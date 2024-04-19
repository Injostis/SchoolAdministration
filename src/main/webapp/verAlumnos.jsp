<%@page import="logica.Materia"%>
<%@page import="logica.Grupo"%>
<%@page import="logica.Estudiante"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>


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
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Grupo</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Grupo</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </tfoot>

                    <%
                        List<Estudiante> listaEstudiantes = (List) request.getSession().getAttribute("listaEstudiantes");
                    %>

                    <tbody>
                        <% for (Estudiante estudiante : listaEstudiantes) {%>
                        <tr>
                            <td id="id_estudiante<%=estudiante.getId_estudiante()%>"><%=estudiante.getId_estudiante()%></td>
                            <td><%=estudiante.getNombre()%></td>
                            <td><%=estudiante.getApellido_paterno()%></td>
                            <td><%=estudiante.getApellido_materno()%></td>
                            <td>
                                <%
                                    Grupo miGrupo = estudiante.getMiGrupo();
                                    out.println(miGrupo.getNombre());
                                %>
                            </td>
                            <td style="display: flex; width: 230px;">
                                <form name="eliminar" action="SvElimAlumnos" method="POST">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red; margin-right:5px;">
                                        <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%=estudiante.getId_estudiante()%>">
                                </form>
                                <form name="editar" action="SvEditAlumnos" method="GET">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px;">
                                        <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%=estudiante.getId_estudiante()%>">
                                </form>
                            </td>
                        </tr>
                        <% } %>
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