<%-- 
    Document   : login
    Created on : May 16, 2020, 10:56:12 PM
    Author     : Steven
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Pizzeria</title>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <body>
        <style>
            body{
                background-color: #232b2b;
            }
            img {
                
                width: 20%;
            }
        </style>
        <div align="center" width="100%">

            <img src="img/LogoW.png" alt="logo">
        </div>

        <div align="center" width="100%" style="margin-top: 3%;">
            <c:if test="${requestScope.resultado=='error'}">
                <div class="alert alert-danger" role="alert">
                    <h2>Error en las credenciales</h2>
                </div>
            </c:if>
            <c:if test="${requestScope.resultado=='errorTipoUsuario'}">
                <div class="alert alert-danger" role="alert">
                    <h2>No tiene los suficientes privilegios para este men&uacute;</h2>
                </div>
            </c:if>
            <div style="width: 25%;" align="left" class="card">
                <form action="login" method="POST" name="frmLogin">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="username">Usuario</label>
                            <input type="text" required class="form-control" name="username" size="45" />
                        </div>
                        <div class="form-group">
                            <label for="pwd">Contraseña</label>
                            <input type="password" required class="form-control" name="pwd" size="45" />
                        </div>
                        <div align="center" class="form-group">
                            <input type="submit" name="btnIniciarSesion" class="btn btn-primary" value="Iniciar Sesion" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
