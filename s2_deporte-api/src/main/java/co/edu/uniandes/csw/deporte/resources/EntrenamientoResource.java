/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.EntrenamientoDTO;
import co.edu.uniandes.csw.deporte.ejb.EntrenamientoLogic;
import co.edu.uniandes.csw.deporte.entities.EntrenamientoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Nicolas De la Hoz
 */
@Path("entrenamientos")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped

public class EntrenamientoResource {
    
    @Inject
    private EntrenamientoLogic logica;
    
    @POST
    public EntrenamientoDTO createEntrenamiento(EntrenamientoDTO entrenamiento) throws BusinessLogicException{
        EntrenamientoEntity entrenamientoEntity=entrenamiento.toEntity();
        entrenamientoEntity= logica.createEntrenamiento(entrenamientoEntity);
        return new  EntrenamientoDTO(entrenamientoEntity);
    }
    @PUT
    public EntrenamientoDTO modifyEntrenamiento(EntrenamientoDTO entrenamiento){
        return entrenamiento;
    }
    
    @DELETE
    @Path("entrenamienoId: \\d+}")
    public void deleteEntrenamiento(@PathParam("entrenamientoId")Long entrenamientoId) throws BusinessLogicException {
        EntrenamientoEntity entidad=logica.find(entrenamientoId);
        if(entidad==null){
            throw new WebApplicationException("Entrenamiento con id: " + entrenamientoId + " no existe", 404);
        }
        logica.delete(entrenamientoId);
    }
    
    @GET
    @Path("{entrenamientoId: \\d+}")
    public EntrenamientoDTO getEntrenamiento(@PathParam("entrenamientoId")Long entrenamientoId) throws BusinessLogicException{
        EntrenamientoEntity entidad=logica.find(entrenamientoId);
        if(entidad==null){
            throw new WebApplicationException("Entrenamiento con id: " + entrenamientoId + " no existe", 404);
        }
        return new EntrenamientoDTO(entidad);
    }
    
   
}
