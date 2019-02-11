/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.CampeonatoDTO;
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

public class CampeonatoResource {
    private static final Logger LOGGER = Logger.getLogger(CampeonatoResource.class.getName());
    
    @POST
    public CampeonatoDTO createCampeonato(CampeonatoDTO pCampeonato)
    {
        return pCampeonato;
    }
}
