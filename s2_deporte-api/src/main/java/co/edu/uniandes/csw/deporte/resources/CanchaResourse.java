/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.CanchaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("canchas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CanchaResourse {
    
    private static final Logger LOGGER = Logger.getLogger(CanchaResourse.class.getName());
    
    @POST
    public CanchaDTO createCancha(CanchaDTO cancha){
        
        return cancha;
    }
}
