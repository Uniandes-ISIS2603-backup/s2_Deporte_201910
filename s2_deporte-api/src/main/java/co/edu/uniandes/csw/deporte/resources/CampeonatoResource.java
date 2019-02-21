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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("campeonato")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped

public class CampeonatoResource {
    private static final Logger LOGGER = Logger.getLogger(CampeonatoResource.class.getName());
    
    @POST
    public CampeonatoDTO createCampeonato(CampeonatoDTO pCampeonato)
    {
        return pCampeonato;
    }
    
    @PUT
    public CampeonatoDTO modifyCampeonato(CampeonatoDTO pCampeonato)
    {
        return pCampeonato;
    }
    
    @DELETE
    public CampeonatoDTO deleteCampeonato(CampeonatoDTO pCampeonato)
    {
        return pCampeonato;
    }
}
