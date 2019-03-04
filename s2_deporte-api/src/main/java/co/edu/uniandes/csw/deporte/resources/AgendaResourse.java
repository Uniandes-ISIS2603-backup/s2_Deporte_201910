/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.AgendaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Santiago Barbosa
 */

@Path("agendas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AgendaResourse {
    
    private static final Logger LOGGER = Logger.getLogger(AgendaResourse.class.getName());
    
    @POST
    @Path("{agendaId : \\d+}")
    public AgendaDTO createPropietario(AgendaDTO agenda){
        return agenda;
    }
    
    @PUT
    @Path("{agendaId : \\d+}")
    public AgendaDTO modifyCancha(AgendaDTO agenda){
        return agenda;
    }
    
    @DELETE
    @Path("{agendaId : \\d+}")
    public AgendaDTO deleteCancha(AgendaDTO agenda){
        return agenda;
    }
}
