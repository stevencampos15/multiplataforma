/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.json.JsonException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Detallespedido;
import servicios.Pedidos;
import servicios.Respuesta;
import servicios.Tipoitems;
import servicios.Usuarios;
import servicios.WsBuscarTipoItem;
import servicios.WsListaDetallesPedido;
import servicios.WsListaPedidos;
import servicios.WsListaTipoItems;
import servicios.WsListaUsuarios;

/**
 *
 * @author Steven
 */
public class CtrlPedidos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        try (PrintWriter out = response.getWriter()) {
            String accionlista = request.getParameter("accionLista");
            String accion = request.getParameter("accion");
            String formulario = request.getParameter("btnFormulario");
            if (accionlista == null) {
                //Muestra lista
                WsListaPedidos pedidos = new WsListaPedidos();
                Respuesta resp = pedidos.getListaPedidos(Respuesta.class);
                ArrayList<Pedidos> lstPedidos = new ArrayList<Pedidos>();
                ArrayList<Detallespedido> lstDetallePedidos = new ArrayList<Detallespedido>();
                
                if (resp.getStatus().equals("ok")) {
                    ArrayList obj = (ArrayList) resp.getListaPedidos();
                    if (obj != null) {
                        for (int i = 0; i < obj.size(); i++) {
                            try {
                                //Traemos el encabezado de los pedidos
                                Pedidos pedido = new Pedidos();
                                pedido = (Pedidos) obj.get(i);
                                lstPedidos.add(pedido);

                                //Buscamos y traemos el detalle
                                WsListaDetallesPedido Detallespedido = new WsListaDetallesPedido();
                                ArrayList<Detallespedido> lstInternoDet = new ArrayList<Detallespedido>();

                                Respuesta respDetalle = Detallespedido.getListaDetallespedido(Respuesta.class, pedido.getIdPedido().toString());
                                if (respDetalle.getStatus().equals("ok")) {
                                    ArrayList objDetalle = (ArrayList) respDetalle.getListaDetallePedidos();
                                    for (int j = 0; j < objDetalle.size(); j++) {
                                        try {
                                            //Traemos el detalle
                                            Detallespedido detalle = new Detallespedido();
                                            detalle = (Detallespedido) objDetalle.get(j);
                                            if (pedido.getIdPedido() == detalle.getPedidos().getIdPedido()) {
                                                lstInternoDet.add(detalle);
                                            }
                                        } catch (JsonException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                
                                for (int j = 0; j < lstInternoDet.size(); j++) {
                                    lstDetallePedidos.add(lstInternoDet.get(j));
                                }
                            } catch (JsonException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    
                    Collections.reverse(lstPedidos);
                    request.setAttribute("listaDetallespedido", lstDetallePedidos);
                    request.setAttribute("accionLista", "mostrar");
                    request.setAttribute("listaPedidos", lstPedidos);

                }

                //Activa el formulario
                if (formulario != null) {
                    request.setAttribute("activarFrm", "formulario");

                    //Mandamos la lista que contiene los tipos de items
                    WsListaTipoItems tipoItems = new WsListaTipoItems();
                    Respuesta respTipo = tipoItems.getListaTipoItems(Respuesta.class);
                    ArrayList<Tipoitems> lstTipoItems = new ArrayList<Tipoitems>();

                    if (respTipo.getStatus().equals("ok")) {
                        ArrayList obj = (ArrayList) respTipo.getListaTipoItems();
                        if (obj != null) {
                            for (int i = 0; i < obj.size(); i++) {
                                try {
                                    Tipoitems tipoItem = new Tipoitems();
                                    tipoItem = (Tipoitems) obj.get(i);
                                    lstTipoItems.add(tipoItem);
                                } catch (JsonException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    request.setAttribute("lstTipoItems", lstTipoItems);
                    //Muestra el formulario en modo editar
                    if (formulario.contains("Editar")) {
                        request.setAttribute("activarFrm", "formulario");

                        //Validamos y mandamos los atributos
                        String idItem = request.getParameter("idItem");
                        String nombreItem = request.getParameter("nombreItem");
                        String tamano = request.getParameter("tamano");
                        String precio = request.getParameter("precio");
                        String descripcion = request.getParameter("descripcion");
                        String idTipoItem = request.getParameter("idTipoItem");

                        //Buscamos el tipo asociado al item para mostrarlo en el control select
                        WsBuscarTipoItem bTipoItem = new WsBuscarTipoItem();
                        Respuesta respBuscarTipo = bTipoItem.getTipoItem(Respuesta.class, idTipoItem);
                        Tipoitems tipoItem = new Tipoitems();
                        if (respBuscarTipo.getStatus().equals("ok")) {
                            tipoItem.setIdTipoItem(respBuscarTipo.getTipoItem().getIdTipoItem());
                            tipoItem.setNombreTipoItem(respBuscarTipo.getTipoItem().getNombreTipoItem());
                            System.out.println(tipoItem.getNombreTipoItem());
                        } else {
                            System.out.println("Tipo Item no encontrado");
                        }

                        //Mandamos la los atributos encontrados
                        request.setAttribute("idItemEditar", idItem);
                        request.setAttribute("nombreItemEditar", nombreItem);
                        request.setAttribute("tamanoEditar", tamano);
                        request.setAttribute("precioEditar", precio);
                        request.setAttribute("descripcionEditar", descripcion);
                        request.setAttribute("tipoItemEditar", tipoItem);
                    }
                }
            }
            rd = request.getRequestDispatcher("pedidos.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
