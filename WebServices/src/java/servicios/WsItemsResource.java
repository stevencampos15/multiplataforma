
package servicios;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.dao.DaoItem;
import modelo.entidad.Items;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("WsItems")
public class WsItemsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsItemsResource
     */
    public WsItemsResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.WsItemsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listaItems")
    public Respuesta getListaItems() {
        Respuesta rs = new Respuesta();
        try {
            List<Items> lista;
            DaoItem di = new DaoItem();
            lista = di.consultarTodo();
            rs.setStatus("ok");
            rs.setListaItems(lista);
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }

    /**
     * POST method for updating or creating an instance of WsItemsResource
     *
     * @param item representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta postInsertar(Items item) {
        Respuesta resp = new Respuesta();
        try {
            DaoItem di = new DaoItem();
            if(di.insertar(item)){
                resp.setStatus("ok");
            }
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return resp;
    }
    
    /**
     * PUT method for updating or creating an instance of WsItemsResource
     *
     * @param item representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta putModificar(Items item) {
        Respuesta resp = new Respuesta();
        try {
            DaoItem di = new DaoItem();
            if(di.modificar(item)){
                resp.setStatus("ok");
            }
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return resp;
    }
    
     /**
     * DELETE method for updating or creating an instance of WsItemsResource
     *
     * @param id representation for the resource
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteItem(@QueryParam("id") int id) {
        try {
            DaoItem di = new DaoItem();
            di.eliminar(id);
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        
    }
    
}
