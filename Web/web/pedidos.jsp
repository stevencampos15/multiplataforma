<%-- 
    Document   : pedidos
    Created on : May 17, 2020, 1:26:04 PM
    Author     : Steven
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="120"/>
        <title>Pedidos</title>
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
            li a.active {
                background-color: #007bff;
                color: white;
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
                        <a class="nav-item nav-link" href="items">Productos</a>
                        <a class="nav-item nav-link active" href="#">Pedidos<span class="sr-only">(current)</span></a>
                    </div>
                </div>
            </nav>
        </div>
        <div align="center" width="100%" id="inicio" style="margin-top: 5%;">
            <h1 class="display-1">Pedidos</h1>
            <br />
            <br />
        </div>
        <c:set var="valorDefecto" value="5" />
        <c:set var="valor" value="${param.valor}" />
        <!-- Tabla para mostrar -->
        <c:if test="${requestScope.accionLista=='mostrar'}">
            <div align="center" style="width: 60%; margin-top: 5%; margin-left: 20%; margin-right: 20%;" class="pagination-container">
                <div class="pagination-container" >
                    <table border="1" cellpadding="5" class="table" style=" margin-bottom: 0 !important;">
                        <thead class="thead-dark">
                            <tr>
                                <th colspan="4"><center><h3>Lista de Pedidos</h3></center></th>
                        <th style="width: 10%">
                            <form method="POST">
                                <select onchange="this.form.submit()"  name="valor" id="selectValor">
                                    <option selected disabled>Mostrar # Entradas</option>
                                    <option value="1" <c:if test="${param.valor==1}">selected</c:if> >1</option>
                                    <option value="2" <c:if test="${param.valor==2}">selected</c:if>  >2</option>
                                    <option value="5" <c:if test="${param.valor==5}">selected</c:if> >5</option>
                                    <option value="10"<c:if test="${param.valor==10}">selected</c:if> >10</option>
                                    </select>
                                </form> 
                            </th>
                            </tr>
                            </thead>
                        </table>
                    <c:set var="conta" value="0" scope="page" />
                    <c:set var="cc" value="0" scope="page" />
                    <c:forEach var="lista" items="${requestScope.listaPedidos}">
                        <c:choose>
                            <c:when test="${valor==null}">
                                <c:if test="${conta%valorDefecto==0}">
                                    <c:set var="cc" scope="page" value="${cc+1}"/>
                                </c:if>
                            </c:when>
                            <c:when test="${valor!=null}">
                                <c:if test="${conta%valor==0}">
                                    <c:set var="cc" scope="page" value="${cc+1}"/>
                                </c:if>
                            </c:when>

                        </c:choose>

                        <c:set var="conta" scope="page" value="${conta+1}"/>
                        <div data-page="${cc}" style="display:none;" >
                            <table border="1" cellpadding="5" class="table" id="myTable">
                                <thead class="thead-dark">
                                    <tr><th class="th-sm"colspan="5"><h3>Pedido #<c:out value="${lista.idPedido}" /></h3></th></tr>
                                </thead>
                                <thead class="thead table-info">
                                    <tr>
                                        <th>Pedido</th>
                                        <th>Usuario</th>
                                        <th colspan="2">Fecha</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>

                                <tr>
                                    <td>${lista.idPedido}</td>
                                    <td>${lista.usuarios.nombreCliente}</td>
                                    <td colspan="2">${lista.fecha}</td>
                                    <td>
                                        <c:set var="count" value="0" scope="page" />
                                        <c:if test="${requestScope.listaDetallespedido != null}">
                                            <c:forEach var="listaDetalle" items="${requestScope.listaDetallespedido}">
                                                <c:if test="${lista.idPedido == listaDetalle.pedidos.idPedido}">
                                                    <c:set var="count" scope="page" value="${(listaDetalle.cantidad * listaDetalle.items.precio) + count}"/>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                        <fmt:formatNumber value = "${count}" type = "currency"/>
                                    </td>
                                </tr>
                                <c:if test="${requestScope.listaDetallespedido != null}">
                                    <thead class="thead table-primary">
                                        <tr>
                                            <th>No.</th>
                                            <th>Producto</th>
                                            <th>Precio</th>
                                            <th>Cantidad</th>
                                            <th>Suma</th>
                                        </tr>
                                    </thead>
                                    <c:set var="contadorDet" value="0" scope="page" />
                                    <c:forEach var="listaDetalle" items="${requestScope.listaDetallespedido}">
                                        <c:if test="${lista.idPedido == listaDetalle.pedidos.idPedido}">
                                            <tr>
                                                <c:set var="contadorDet" scope="page" value="${contadorDet+1}"/>
                                                <td>${contadorDet}</td>
                                                <td>${listaDetalle.items.nombreItem}</td>
                                                <td><fmt:formatNumber value = "${listaDetalle.items.precio}" type = "currency"/></td>
                                                <td>${listaDetalle.cantidad}</td>
                                                <td><fmt:formatNumber value = "${listaDetalle.cantidad * listaDetalle.items.precio}" type = "currency"/></td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>

                                </c:if>
                                <tr style="height: 3em; background-color: #000;">
                                    <td colspan="5" ></td>
                                </tr>
                            </table>
                        </div>
                    </c:forEach>     
                    <div class="pagination pagination-centered" style="margin: auto; width: 50%;">
                        <ul class="pagination justify-content-center">
                            <li class="page-item" data-page="-" ><a class="page-link" href="#" >&lt;</a></li>
                                <c:set var="conta" value="0" scope="page" />
                                <c:set var="cc" value="0" scope="page" />
                                <c:forEach var="lista" items="${requestScope.listaPedidos}">
                                    <c:set var="conta" scope="page" value="${conta+1}"/>
                                    <c:choose>
                                        <c:when test="${valor==null}">
                                            <c:if test="${conta%valorDefecto==0}">
                                                <c:set var="cc" scope="page" value="${cc+1}"/>
                                            <li class="page-item" data-page="${cc}"><a <c:if test="${cc==1}">id="a1"</c:if> class="page-link" href="#inicio" >${cc}</a></li>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${valor!=null}">
                                            <c:if test="${conta%valor==0}">
                                                <c:set var="cc" scope="page" value="${cc+1}"/>
                                            <li class="page-item" data-page="${cc}"><a <c:if test="${cc==1}">id="a1"</c:if> class="page-link" href="#inicio" >${cc}</a></li>
                                            </c:if>
                                        </c:when>

                                </c:choose>

                                <c:set var="res" value="${conta%5}" scope="page" />
                            </c:forEach>
                            <c:if test="${res>0}">
                                <li class="page-item" data-page="${cc+1}"><a class="page-link" href="#" >${cc+1}</a></li>
                                </c:if>
                            <li class="page-item" data-page="+"><a class="page-link" href="#" >&gt;</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </c:if>
        <script>
            var paginationHandler = function () {
                // Guardar el contenedor de paginacion para seleccionarlo sola una vez
                var $paginationContainer = $(".pagination-container"),
                        $pagination = $paginationContainer.find('.pagination ul');
                // evento clic
                $pagination.find("li a").on('click.pageChange', function (e) {
                    e.preventDefault();
                    //obtiene el atributo de la página de datos del padre li y la página actual
                    var parentLiPage = $(this).parent('li').data("page"),
                            currentPage = parseInt($(".pagination-container div[data-page]:visible").data('page')),
                            numPages = $paginationContainer.find("div[data-page]").length;
                    // se asegura que no hacen clic en la misma pagina
                    if (parseInt(parentLiPage) !== parseInt(currentPage)) {
                        // esconde la pagina actual
                        $paginationContainer.find("div[data-page]:visible").hide();
                        if (parentLiPage === '+') {
                            // pagina siguiente
                            $paginationContainer.find("div[data-page=" + (currentPage + 1 > numPages ? numPages : currentPage + 1) + "]").show();
                        } else if (parentLiPage === '-') {
                            // pagina anterior
                            $paginationContainer.find("div[data-page=" + (currentPage - 1 < 1 ? 1 : currentPage - 1) + "]").show();
                        } else {
                            // pagina en especifico
                            $paginationContainer.find("div[data-page=" + parseInt(parentLiPage) + "]").show();
                            location.href = "#inicio";
                        }
                    }
                });
            };
            $(document).ready(paginationHandler);

            $('li a').click(function (e) {
                e.preventDefault();
                $('a').removeClass('active');
                $(this).addClass('active');
            });
            $(document).ready(function () {
                var $paginationContainer = $(".pagination-container"),
                        $pagination = $paginationContainer.find('.pagination ul');
                $paginationContainer.find("div[data-page=" + 1 + "]").show();
                document.getElementById("a1").setAttribute("class", "page-link active");

            });
        </script>
    </body>
</html>
