<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body id="page-top">

    <!-- Validación Sesión -->
    <%
        /*String username = (String) request.getSession().getAttribute("username");
        
        if(username == null) {
            response.sendRedirect("sinLogin.jsp");
        }*/
    %>

    <!-- Page Wrapper -->
    <div id="wrapper">
        
        <%@include file="sidebar.jsp" %>
        
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
                       
            <!-- Main Content -->
            <div id="content">

                <%@include file="topbar.jsp" %>
                
                <!-- Begin Page Content -->
                <div class="container-fluid">
