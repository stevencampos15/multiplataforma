<%-- 
    Document   : menuPrincipal
    Created on : May 17, 2020, 12:24:31 PM
    Author     : Steven
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <body>
        
        <div>
            <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="#">PizzeriaDSA</a>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link active" href="#">Inicio<span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="usuarios">Usuarios</a>
                        <a class="nav-item nav-link" href="items">Productos</a>
                        <a class="nav-item nav-link" href="pedidos">Pedidos</a>
                    </div>
                </div>
            </nav>
        </div>
        <div align="center" width="100%" style="margin-top: 5%; color:#fff;">
            <c:if test="${requestScope.usuario!=null}">
                <h1>Bienvenido <c:out value="${requestScope.usuario.getNombreCliente()}" /> <c:out value="${requestScope.usuario.getApellidoCliente()}" /></h1>
            </c:if>
        </div>
        <style>
            body{
                background-color: #000;
            }
            img {

                width: 20%;
            }
        </style>
        <div align="center" width="100%">

            <img src="img/LogoW.png" alt="logo">
        </div>
    </body>
</html>
