/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.dao.DaoUsuario;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("wsLogin")
public class WsLoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsLoginResource
     */
    public WsLoginResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.WsLoginResource
     *
     * @param usuario
     * @param pwd
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/login")
    public Respuesta getLogin(@QueryParam("usuario") String usuario, @QueryParam("pwd") String pwd) {
        Respuesta rs = new Respuesta();
        try {
            DaoUsuario du = new DaoUsuario();
            rs.setUsuario(du.login(usuario, pwd));
        } catch (Exception e) {
            System.out.println("Error al llamar el ws :" + e);
        }
        return rs;
    }

    /**
     * PUT method for updating or creating an instance of WsLoginResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
