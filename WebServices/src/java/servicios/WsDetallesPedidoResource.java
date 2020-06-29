/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.dao.DaoDetallepedido;
import modelo.entidad.Detallespedido;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("WsDetallesPedido")
public class WsDetallesPedidoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsDetallesPedidoResource
     */
    public WsDetallesPedidoResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.WsDetallesPedidoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of WsDetallesPedidoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    /**
     * POST method for updating or creating an instance of WsItemsResource
     *
     * @param dp representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta postInsertar(Detallespedido dp) {
        Respuesta resp = new Respuesta();
        try {
            DaoDetallepedido ddp = new DaoDetallepedido();
            if(ddp.insertar(dp)){
                resp.setStatus("ok");
            }
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return resp;
    }
}
