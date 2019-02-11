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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

/**
 *
 * @author estudiante
 */
public class EntrenamientoResource {
    private static final Logger LOGGER = Logger.getLogger(EntrenamientoResource.class.getName());
    
    @POST
    public EntrenamientoDTO createFranja(EntrenamientoDTO entrenamiento){
        return entrenamiento;
    }
    
   
}
