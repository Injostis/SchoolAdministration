<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    
    <!-- Nav Item - Maestros -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMaestros"
           aria-expanded="true" aria-controls="collapseMaestros">
            <i class="fas fa-solid fa-user"></i>
            <span>Maestros</span>
        </a>
        <div id="collapseMaestros" class="collapse" aria-labelledby="headingMaestros" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Acciones:</h6>
                <a class="collapse-item" href="SvMaestros">Ver Maestros</a>
                <a class="collapse-item" href="SvMaestros?accion=altaMaestros">Alta Maestros</a>
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
                <a class="collapse-item" href="SvAlumnos?accion=altaEstudiantes">Alta Alumnos</a>
            </div>
        </div>
    </li>
    
    <!-- Nav Item - Grupos -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseGrupos"
           aria-expanded="true" aria-controls="collapseGrupos">
            <i class="fas fa-solid fa-user"></i>
            <span>Grupos</span>
        </a>
        <div id="collapseGrupos" class="collapse" aria-labelledby="headingGrupos"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Acciones:</h6>
                <a class="collapse-item" href="SvGrupos">Ver Grupos</a>
                <a class="collapse-item" href="SvGrupos?accion=altaGrupos">Alta Grupos</a>
            </div>
        </div>
    </li>
    
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
                <a class="collapse-item" href="altaMaterias.jsp">Alta Materias</a>
            </div>
        </div>
    </li>
    
    <!-- Nav Item - Trabajos -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTrabajos"
           aria-expanded="true" aria-controls="collapseTrabajos">
            <i class="fas fa-solid fa-user"></i>
            <span>Trabajos</span>
        </a>
        <div id="collapseTrabajos" class="collapse" aria-labelledby="headingTrabajos"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Acciones:</h6>
                <a class="collapse-item" href="">Ver Trabajos</a>
                <a class="collapse-item" href="altaTrabajos.jsp">Alta Trabajos</a>
            </div>
        </div>
    </li>
    
    <!-- Nav Item - Calificaciones -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCalificaciones"
           aria-expanded="true" aria-controls="collapseCalificaciones">
            <i class="fas fa-solid fa-user"></i>
            <span>Calificaciones</span>
        </a>
        <div id="collapseCalificaciones" class="collapse" aria-labelledby="headingCalificaciones"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Acciones:</h6>
                <a class="collapse-item" href="">Ver Calificaciones</a>
                <a class="collapse-item" href="altaCalificaciones.jsp">Alta Calificaciones</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

</ul>
<!-- End of Sidebar -->