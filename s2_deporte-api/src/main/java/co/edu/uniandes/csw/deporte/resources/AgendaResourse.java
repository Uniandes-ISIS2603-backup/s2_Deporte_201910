/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.AgendaDTO;
import co.edu.uniandes.csw.deporte.ejb.AgendaLogic;
import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
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
 * @author Santiago Barbosa
 */
@Path("agendas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AgendaResourse {

    private static final Logger LOGGER = Logger.getLogger(AgendaResourse.class.getName());

    @Inject
    AgendaLogic logica;
    @POST
    public AgendaDTO createAgenda(AgendaDTO agenda) throws BusinessLogicException{
        AgendaEntity entity = agenda.toEntity();
        entity = logica.create(entity);
        return new AgendaDTO(entity);
    }

    @PUT
    public AgendaDTO modifyAgenda(AgendaDTO agenda) {
        return agenda;
    }

    @DELETE
    @Path("{agendaId : \\d+}")
    public void deleteAgenda(@PathParam("agendaId")Long id) throws WebApplicationException, BusinessLogicException{
        AgendaEntity entity = logica.find(id);
        if(entity == null)
        {
            throw new WebApplicationException("Agenda con id: " + id + " no existe", 404);
        }
        logica.delete(id);
    }
    
    @GET
    @Path("{agendaId : \\d+}")
    public AgendaDTO getAgenda(@PathParam("agendaId") Long id) throws WebApplicationException, BusinessLogicException{
        AgendaEntity entity = logica.find(id);
        if(entity == null){
            throw new WebApplicationException("Agenda con id: " + id + " no existe", 404);
        }
        return new AgendaDTO(entity);
        
    }
}
