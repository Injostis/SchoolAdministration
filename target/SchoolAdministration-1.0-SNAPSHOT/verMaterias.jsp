<%@page import="logica.Materia"%>
<%@page import="logica.Maestro"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyprimeraparte.jsp" %>


<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Ver Materias</h1>
    <p class="mb-4">Aquí se puede ver una lista de las materias registradas</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Materias</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </tfoot>

                    <%
                        List<Materia> listaMaterias = (List) request.getSession().getAttribute("listaMaterias");
                    %>

                    <tbody>
                        <% for (Materia materia : listaMaterias) {%>
                        <tr>
                            <td id="id_materia<%=materia.getId_materia()%>"><%=materia.getId_materia()%></td>
                            <td><%=materia.getNombre()%></td>
                            <td style="display: flex; width: 230px;">
                                <form name="eliminar" action="SvElimMaterias" method="POST">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red; margin-right:5px;">
                                        <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%=materia.getId_materia()%>">
                                </form>
                                <form name="editar" action="SvEditMaterias" method="GET">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px;">
                                        <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%=materia.getId_materia()%>">
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