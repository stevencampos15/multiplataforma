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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.dao.DaoUsuario;
import modelo.entidad.Usuarios;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("WsUsuarios")
public class WsUsuariosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsUsuariosResource
     */
    public WsUsuariosResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.WsUsuariosResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listaUsuarios")
    public Respuesta getListaUsuarios() {
        Respuesta rs = new Respuesta();
        try {
            List<Usuarios> lista;
            DaoUsuario du = new DaoUsuario();
            lista = du.consultarTodo();
            rs.setStatus("ok");
            rs.setListaUsuarios(lista);
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }

    /**
     * POST method for updating or creating an instance of WsItemsResource
     *
     * @param us representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta postInsertar(Usuarios us) {
        Respuesta resp = new Respuesta();
        try {
            DaoUsuario du = new DaoUsuario();
            if(du.insertar(us)){
                resp.setStatus("ok");
            }
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return resp;
    }
}
