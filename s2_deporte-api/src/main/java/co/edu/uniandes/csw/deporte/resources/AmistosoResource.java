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
import javax.ws.rs.Produces;

/**
 *
 * @author Nicolas De la Hoz
 */

@Path("amistosos")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped
public class AmistosoResource {
    private static final Logger LOGGER = Logger.getLogger(AmistosoResource.class.getName());
   
    @Inject
    private AmistosoLogic logica;
    
    
    @POST
    public AmistosoDTO createAmistoso(AmistosoDTO amistoso) throws BusinessLogicException{
        AmistosoEntity amistosoEntity=amistoso.toEntity();
        amistosoEntity= logica.createAmistoso(amistosoEntity);
        return new AmistosoDTO(amistosoEntity);
    }
    
    @PUT
    public AmistosoDTO modifyEntrenamiento(AmistosoDTO amistoso){
        return amistoso;
    }
    
    @DELETE
    //@Path("{amistosoId: \\d+}")
    public AmistosoDTO deleteEntrenamiento(AmistosoDTO amistoso){
        return amistoso;
    }
    
    @GET
    //@Path("{amistosoId: \\d+}")
    public AmistosoDTO getEntrenamiento(AmistosoDTO amistoso){
        return amistoso;
    }
}