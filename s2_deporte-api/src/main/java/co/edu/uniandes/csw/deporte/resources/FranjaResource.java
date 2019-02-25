/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.FranjaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Date;


/**
 *
 * @author Santiago Barbosa
 */

@Path ("franjas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FranjaResource {
    private static final Logger LOGGER = Logger.getLogger(FranjaResource.class.getName());
    
    @POST
    public FranjaDTO createFranja(FranjaDTO franja){
        return franja;
    }
    
    @GET
    public FranjaDTO getFranja(){
        return null;
    }
    @DELETE
    public FranjaDTO deleteFranja(FranjaDTO franja){
        return franja;
    }
}
