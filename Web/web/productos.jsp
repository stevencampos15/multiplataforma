<%-- 
    Document   : productos
    Created on : May 17, 2020, 1:21:02 PM
    Author     : Steven
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
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
                        <a class="nav-item nav-link" href="usuarios">Usuarios</a>
                        <a class="nav-item nav-link active" href="#">Productos<span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="pedidos">Pedidos</a>
                    </div>
                </div>
            </nav>
        </div>

        <!-- Formulario para Agregar o Editar -->
        <div align="center" width="100%" style="margin-top: 5%;">
            <h1 class="display-1">Productos</h1>
            <br />
            <br />
            <c:if test="${requestScope.exito!=null}">
                <div class="alert alert-success" role="alert">
                    <h2><c:out value="${requestScope.exito}" /></h2>
                </div>
                <script>
                    setTimeout(function () {
                        window.location.replace("items");
                    }, 1000);
                </script>
            </c:if>
            <form method="POST" action="items">
                <input type="submit" name="btnFormulario" class="btn btn-primary btn-lg" value="Nuevo">
            </form>

            <c:if test="${requestScope.activarFrm=='formulario'}">
                <div style="width: 50%; color: #000;" align="left" class="card">
                    <form action="items" method="POST" name="frmItem">
                        <div class="form-group card-header">
                            <h2>
                                <c:if test="${requestScope.idItemEditar != null}">
                                    Editar Producto
                                </c:if>
                                <c:if test="${requestScope.idItemEditar == null}">
                                    Nuevo Producto
                                </c:if>
                            </h2>
                        </div>
                        <c:if test="${requestScope.idItemEditar != null}" >
                            <input type="hidden"  name="idItem" value="<c:if test="${requestScope.idItemEditar != null}" ><c:out value='${requestScope.idItemEditar}' /></c:if>"/>
                        </c:if>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="nombreItem">Nombre</label>
                                <input type="text" required class="form-control" name="nombreItem" size="45" value="<c:if test="${requestScope.idItemEditar != null}" ><c:out value='${requestScope.nombreItemEditar}' /></c:if>" />
                                </div>
                                <div class="form-group">
                                    <label for="tamano">Tamaño</label>
                                    <input type="text" class="form-control" required name="tamano" size="45" value="<c:if test="${requestScope.idItemEditar != null}" ><c:out value='${requestScope.tamanoEditar}' /></c:if>"/>
                                </div>
                                <div class="form-group">
                                    <label for="precio">Precio</label>
                                    <input type="number" min="0.01" step="any" class="form-control" required name="precio" size="45" value="<c:if test="${requestScope.idItemEditar != null}" ><c:out value='${requestScope.precioEditar}' /></c:if>"/>
                                </div>
                                <div class="form-group">
                                    <label for="descripcion">Descripción</label>
                                    <input type="text" class="form-control" required name="descripcion" size="45" value="<c:if test="${requestScope.idItemEditar != null}" ><c:out value='${requestScope.descripcionEditar}' /></c:if>"/>
                                </div>
                                <div class="form-group">
                                    <label for="tipoItem">Tipo de Producto</label>
                                    <select class="form-control" name="tipoItem" required>
                                    <c:choose>
                                        <c:when test="${requestScope.idItemEditar!=null}">
                                            <option value="${tipoItemEditar.idTipoItem}" selected>${tipoItemEditar.nombreTipoItem}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option selected disabled>Seleccione un Tipo</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="lstTipoItems" items="${requestScope.lstTipoItems}">
                                        <option value="${lstTipoItems.idTipoItem}">${lstTipoItems.nombreTipoItem}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <c:choose>
                                    <c:when test="${requestScope.idItemEditar!=null}">
                                        <input  type="submit" name="accion" class="btn btn-warning" value="Editar" />
                                    </c:when>
                                    <c:otherwise>
                                        <input  type="submit" name="accion" class="btn btn-primary" value="Insertar" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </form>
                </div>
            </c:if>
        </div> 

        <!-- Tabla para mostrar -->
        <c:if test="${requestScope.accionLista=='mostrar'}">
            <div align="center" style="width: 60%; margin-top: 5%; margin-left: 20%; margin-right: 20%;">
                <table border="1" cellpadding="5" class="table">
                    <thead class="thead-dark">
                        <tr><th colspan="7"><h3>Lista de Productos</h3></th></tr>
                    </thead>
                    <thead class="thead-light">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Tamaño</th>
                            <th>Precio</th>
                            <th>Descripcion</th>
                            <th>Tipo Producto</th>
                            <th>Acci&oacute;n</th>
                        </tr>
                    </thead>
                    <c:forEach var="lista" items="${requestScope.listaItems}">
                        <tr>
                            <td>${lista.idItem}</td>
                            <td>${lista.nombreItem}</td>
                            <td>${lista.tamano}</td>
                            <td><fmt:formatNumber value = "${lista.precio}" type = "currency"/></td>
                            <td>${lista.descripcion}</td>
                            <td>${lista.tipoitems.nombreTipoItem}</td>
                            <td>
                                <form method="POST" action="items">
                                    <input type="hidden" name="idItem" value='${lista.idItem}' />
                                    <input type="hidden" name="nombreItem" value='${lista.nombreItem}' />
                                    <input type="hidden" name="tamano" value='${lista.tamano}' />
                                    <input type="hidden" name="precio" value='${lista.precio}' /> 
                                    <input type="hidden" name="descripcion" value='${lista.descripcion}' /> 
                                    <input type="hidden" name="idTipoItem" value='${lista.tipoitems.idTipoItem}' /> 
                                    <input type="submit" class="btn btn-warning" name="btnFormulario" value="Editar" style="float: left;"/>
                                </form>
                                <form method="POST" action="items">
                                    <input type="hidden" name="idItem" value='${lista.idItem}'/>
                                    <input type="submit" class="btn btn-danger" name="accion" value="Eliminar" style="float: right;" />
                                </form>                    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </body>
</html>
