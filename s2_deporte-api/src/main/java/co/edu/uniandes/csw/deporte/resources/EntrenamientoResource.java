/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.EntrenamientoDTO;
import co.edu.uniandes.csw.deporte.dtos.FranjaDTO;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Nicolas De la Hoz
 */
@Path("entrenamientos")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped

public class EntrenamientoResource {
    private static final Logger LOGGER = Logger.getLogger(EntrenamientoResource.class.getName());
    
    @POST
    public EntrenamientoDTO createEntrenamiento(EntrenamientoDTO entrenamiento){
        return entrenamiento;
    }
    @PUT
    public EntrenamientoDTO modifyEntrenamiento(EntrenamientoDTO entrenamiento){
        return entrenamiento;
    }
    
    @DELETE
    //@Path("{entrenamientoId: \\d+}")
    public EntrenamientoDTO deleteEntrenamiento(EntrenamientoDTO entrenamiento){
        return entrenamiento;
    }
    
    @GET
    //@Path("{entrenamientoId: \\d+}")
    public EntrenamientoDTO getEntrenamiento(EntrenamientoDTO entrenamiento){
        return entrenamiento;
    }
    
   
}
