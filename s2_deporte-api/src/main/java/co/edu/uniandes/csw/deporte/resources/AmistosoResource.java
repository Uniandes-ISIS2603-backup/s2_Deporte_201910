/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.AmistosoDTO;
import co.edu.uniandes.csw.deporte.dtos.EntrenamientoDTO;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 *
 * @author estudiante
 */
public class AmistosoResource {
    private static final Logger LOGGER = Logger.getLogger(AmistosoResource.class.getName());
    
    @POST
    public AmistosoDTO createAmistoso(AmistosoDTO amistoso){
        return amistoso;
    }
    @PUT
    public AmistosoDTO modifyEntrenamiento(AmistosoDTO amistoso){
        return amistoso;
    }
    
    @DELETE
    public AmistosoDTO deleteEntrenamiento(AmistosoDTO amistoso){
        return amistoso;
    }
    
    @GET
    public AmistosoDTO getEntrenamiento(AmistosoDTO amistoso){
        return amistoso;
    }
}