/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.AgendaDTO;
import co.edu.uniandes.csw.deporte.dtos.AgendaDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.AgendaLogic;
import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    @Path("{agendaId : \\d+}")
    public AgendaDetailDTO modifyAgenda(@PathParam("agendaId")Long id, AgendaDetailDTO agenda) {
        LOGGER.log(Level.INFO, "AgendaResource updateBook: input: id: {0} , book: {1}", new Object[]{id, agenda});
        agenda.setId(id);
        if (logica.find(id) == null) {
            throw new WebApplicationException("El recurso /agendas/" + id + " no existe.", 404);
        }
        AgendaDetailDTO detailDTO = new AgendaDetailDTO(logica.update(id, agenda.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO);
        return detailDTO;
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
    public AgendaDetailDTO getAgenda(@PathParam("agendaId") Long id) throws WebApplicationException, BusinessLogicException{
        AgendaEntity entity = logica.find(id);
        if(entity == null){
            throw new WebApplicationException("Agenda con id: " + id + " no existe", 404);
        }
        return new AgendaDetailDTO(entity);
        
    }
    
    @GET
    public List<AgendaDetailDTO> getAgendas() {
        LOGGER.info("AgendaResource getAgenda: input: void");
        List<AgendaDetailDTO> listaAgendas = listEntity2DetailDTO(logica.findAll());
        LOGGER.log(Level.INFO, "AgendaResource getAgenda: output: {0}", listaAgendas);
        return listaAgendas;
    }
    
    @GET
    @Path("filtroCancha/{canchaId : \\d+}")
       public List<AgendaDetailDTO> getAgendasPorCancha(@PathParam("canchaId") Long id){
       List<AgendaDetailDTO> listaAgendas = listEntity2DetailDTO(logica.findAgendasPorCancha(id));
        return listaAgendas;
    }
    
    private List<AgendaDetailDTO> listEntity2DetailDTO(List<AgendaEntity> entityList) {
        List<AgendaDetailDTO> list = new ArrayList<>();
        for (AgendaEntity entity : entityList) {
            list.add(new AgendaDetailDTO(entity));
        }
        return list;
    }
}
