/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.dao.DaoDetallepedido;
import modelo.dao.DaoPedido;
import modelo.entidad.Detallespedido;
import modelo.entidad.Pedidos;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("WsPedidos")
public class WsPedidosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsPedidosResource
     */
    public WsPedidosResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.WsPedidosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listaPedidos")
    public Respuesta getListaPedidos() {
        Respuesta rs = new Respuesta();
        try {
            List<Pedidos> lista;
            DaoPedido dp = new DaoPedido();
            lista = dp.consultarTodo();
            rs.setStatus("ok");
            rs.setListaPedidos(lista);
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listaDetallespedido")
    public Respuesta getListaDetallespedido(@QueryParam("idPedido") String idPedido) {
        Respuesta rs = new Respuesta();
        try {
            int id = Integer.parseInt(idPedido);
            List<Detallespedido> lista;
            DaoDetallepedido ddp = new DaoDetallepedido();
            lista = ddp.consultaPorPedido(id);
            rs.setStatus("ok");
            rs.setListaDetallePedidos(lista);
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }
    
        /**
     * POST method for updating or creating an instance of WsPedidosResource
     *
     * @param p representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta postInsertar(Pedidos p) {
        Respuesta resp = new Respuesta();
        try {
            DaoPedido dp = new DaoPedido();
            if(dp.insertar(p)){
                resp.setStatus("ok");
            }
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return resp;
    }

    /**
     * PUT method for updating or creating an instance of WsPedidosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
