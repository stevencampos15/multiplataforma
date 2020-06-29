<%-- 
    Document   : usuarios
    Created on : May 17, 2020, 1:26:22 PM
    Author     : Steven
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <body>
        <style>
            body{
                background-color: #000;
                color: #fff;
                
            }
            img {
                
                width: 20%;
            }
            .table{
                
                background-color: #fff;
            }
        </style>
        <!-- Menu de navegacion -->
        <div>
            <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="#">PizzeriaDSA</a>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link" href="menuPrincipal.jsp">Inicio</a>
                        <a class="nav-item nav-link active" href="#">Usuarios<span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="items">Productos</a>
                        <a class="nav-item nav-link" href="pedidos">Pedidos</a>
                    </div>
                </div>
            </nav>
        </div>
        
        <div align="center" width="100%" style="margin-top: 5%;">
            <h1 class="display-1">Usuarios</h1>
            <br />
            <br />
        </div>

        <!-- Tabla para mostrar -->
    <c:if test="${requestScope.accionLista=='mostrar'}">
        <div align="center" style="width: 60%; margin-top: 5%; margin-left: 20%; margin-right: 20%;">
            <table border="1" cellpadding="5" class="table">
                <thead class="thead-dark">
                    <tr><th colspan="8"><h3>Lista de Usuarios</h3></th></tr>
                </thead>
                <thead class="thead-light">
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Documento</th>
                        <th>Tipo de Usuario</th>
                    </tr>
                </thead>
                <c:forEach var="lista" items="${requestScope.listaUsuarios}">
                    <tr>
                        <td>${lista.idUsuario}</td>
                        <td>${lista.username}</td>
                        <td>${lista.pwd}</td>
                        <td>${lista.nombreCliente}</td>
                        <td>${lista.apellidoCliente}</td>
                        <td>${lista.documento}</td>
                        <td>${lista.tipousuarios.nombreTipoUsuario}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</body>
</html>
