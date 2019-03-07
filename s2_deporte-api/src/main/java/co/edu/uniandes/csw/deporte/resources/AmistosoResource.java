/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.AmistosoDTO;
import co.edu.uniandes.csw.deporte.dtos.EntrenamientoDTO;
import co.edu.uniandes.csw.deporte.ejb.AmistosoLogic;
import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
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

@Path("amistosos")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped
public class AmistosoResource {
    public static final Logger LOGGER = Logger.getLogger(AmistosoResource.class.getName());
   
    @Inject
    public AmistosoLogic logica;
    
    
    @POST
    public AmistosoDTO createAmistoso(AmistosoDTO amistoso) throws BusinessLogicException{
        AmistosoEntity amistosoEntity=amistoso.toEntity();
        amistosoEntity= logica.createAmistoso(amistosoEntity);
        return new AmistosoDTO(amistosoEntity);
    }
    
    @PUT
    public AmistosoDTO modifyAmistoso(AmistosoDTO amistoso){
        return amistoso;
    }
    
    @DELETE
    @Path("{amistosoId: \\d+}")
    public void deleteAmistoso(@PathParam("amistosoId") Long amistosoId) throws WebApplicationException, BusinessLogicException{
        AmistosoEntity entidad=logica.find(amistosoId);
        if(entidad==null){
            throw new WebApplicationException("Amistoso con id: " + amistosoId + " no existe", 404);
        }
        logica.delete(amistosoId);
    }
    
    @GET
    @Path("{amistosoId: \\d+}")
    public AmistosoDTO getAmistosao(@PathParam("amistosoId") Long amistosoId) throws WebApplicationException, BusinessLogicException{
        AmistosoEntity entidad=logica.find(amistosoId);
        if(entidad==null){
            throw new WebApplicationException("Amistoso con id: " + amistosoId + " no existe", 404);
        }
        return new AmistosoDTO(entidad);
    }
}