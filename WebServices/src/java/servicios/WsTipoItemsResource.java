/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.dao.DaoTipoitem;
import modelo.entidad.Tipoitems;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("WsTipoItems")
public class WsTipoItemsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsTipoItemsResource
     */
    public WsTipoItemsResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.WsTipoItemsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listaTipoItems")
    public Respuesta getListaTipoItems() {
        Respuesta rs = new Respuesta();
        try {
            List<Tipoitems> lista;
            DaoTipoitem dti = new DaoTipoitem();
            lista = dti.consultarTodo();
            rs.setStatus("ok");
            rs.setListaTipoItems(lista);
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoItem")
    public Respuesta getTipoItem(@QueryParam("idTipoItem") int idTipoItem) {
        Respuesta rs = new Respuesta();
        try {
            DaoTipoitem dti = new DaoTipoitem();
            rs.setTipoItem(dti.consultaTipoitem(idTipoItem));;
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }
    
    

    /**
     * PUT method for updating or creating an instance of WsTipoItemsResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
