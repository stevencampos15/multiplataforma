package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Items;
import servicios.Respuesta;
import servicios.Tipoitems;
import servicios.WsBuscarTipoItem;
import servicios.WsCrudItems;
import servicios.WsItems;
import servicios.WsListaTipoItems;

/**
 *
 * @author Steven
 */
@WebServlet(name = "CtrlItems", urlPatterns = {"/items"})
public class CtrlItems extends HttpServlet {

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
                WsItems items = new WsItems();
                Respuesta resp = items.getListaItems(Respuesta.class);
                ArrayList<Items> lstItems = new ArrayList<Items>();

                if (resp.getStatus().equals("ok")) {
                    ArrayList obj = (ArrayList) resp.getListaItems();
                    if (obj != null) {
                        for (int i = 0; i < obj.size(); i++) {
                            try {
                                Items item = new Items();
                                item = (Items) obj.get(i);
                                lstItems.add(item);
                            } catch (JsonException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    request.setAttribute("accionLista", "mostrar");
                    request.setAttribute("listaItems", lstItems);
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

            if (accion != null) {
                //Generamos accion dependiendo del crud
                if (accion.contains("Insertar") || accion.contains("Editar")) {
                    //Capturamos los datos a ingresar en el crud
                    String nombreItem = request.getParameter("nombreItem");
                    String tamano = request.getParameter("tamano");
                    float precio = Float.valueOf(request.getParameter("precio"));
                    String descripcion = request.getParameter("descripcion");
                    String idTipoItem = request.getParameter("tipoItem");

                    //Buscamos el tipo asociado al item para mostrarlo en el control select
                    WsBuscarTipoItem bTipoItem = new WsBuscarTipoItem();
                    Respuesta respBuscarTipo = bTipoItem.getTipoItem(Respuesta.class, idTipoItem);
                    Tipoitems tipoItem = new Tipoitems();
                    if (respBuscarTipo.getStatus().equals("ok")) {
                        tipoItem.setIdTipoItem(respBuscarTipo.getTipoItem().getIdTipoItem());
                        tipoItem.setNombreTipoItem(respBuscarTipo.getTipoItem().getNombreTipoItem());
                    } else {
                        System.out.println("Tipo Item no encontrado");
                    }
                    //Creamos el objeto item 

                    if (request.getParameter("accion").contains("Insertar")) {
                        //Agregar Item
                        Items item = new Items(tipoItem, nombreItem, tamano, precio, descripcion);
                        //Insertamos el item
                        WsCrudItems crudItems = new WsCrudItems();
                        Respuesta respCrud = crudItems.postInsertar(item, Respuesta.class);
                        String exito = "¡Error, no se ha insertado!";
                        if (respCrud.getStatus().equals("ok")) {
                            exito = "¡Se ha insertado con éxito!";
                        }

                        request.setAttribute("exito", exito);
                    } else if (accion.contains("Editar")) {
                        //Modificar Item
                        //Seteamos los elementos necesarios para enviar
                        int idItem = Integer.parseInt(request.getParameter("idItem"));
                        Items item = new Items();
                        item.setIdItem(idItem);
                        item.setNombreItem(nombreItem);
                        item.setTamano(tamano);
                        item.setDescripcion(descripcion);
                        item.setPrecio(precio);
                        item.setTipoitems(tipoItem);

                        WsCrudItems crudItems = new WsCrudItems();
                        Respuesta respCrud = crudItems.putModificar(item, Respuesta.class);
                        String exito = "¡Error, no se ha modificado!";
                        if (respCrud.getStatus().equals("ok")) {
                            exito = "¡Se ha modificado con éxito!";
                        }

                        request.setAttribute("exito", exito);

                    }
                } else if (accion.contains("Eliminar")) {
                    //Eliminamos el producto
                    String idItem = request.getParameter("idItem");
                    WsCrudItems crudItems = new WsCrudItems();
                    crudItems.deleteItem(idItem);
                    String exito = "¡Se ha eliminado con éxito!";
                    request.setAttribute("exito", exito);
                }

            }
            rd = request.getRequestDispatcher("productos.jsp");
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
