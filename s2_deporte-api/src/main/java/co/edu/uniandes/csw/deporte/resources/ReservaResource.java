/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.ReservaDTO;
import co.edu.uniandes.csw.deporte.ejb.ReservaLogic;
import co.edu.uniandes.csw.deporte.entities.ReservaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nicolas De la Hoz
 */

@Path ("reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ReservaResource {;
        
    @Inject
    ReservaLogic logica;
    
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws BusinessLogicException{
        
        ReservaDTO reservaDTO= new ReservaDTO(logica.createReserva(reserva.toEntity()));

        return reservaDTO;
    }
    
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDTO modifyReserva(@PathParam("reservaId") Long reservaId, ReservaDTO reserva) throws BusinessLogicException{
        reserva.setId(reservaId);
        if(logica.getReserva(reservaId)== null){
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        return new ReservaDTO(logica.update(reservaId, reserva.toEntity()));
    }
    
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId")Long reservaId) throws BusinessLogicException {
        ReservaEntity entidad=logica.find(reservaId);
        if(entidad==null){
            throw new WebApplicationException("Entrenamiento con id: " + reservaId + " no existe", 404);
        }
        logica.delete(reservaId);
    }
    
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDTO getReserva(@PathParam("reservaId")Long reservaId) throws BusinessLogicException{
        ReservaEntity entidad=logica.find(reservaId);
        if(entidad==null){
            throw new WebApplicationException("Entrenamiento con id: " + reservaId + " no existe", 404);
        }
        return new ReservaDTO(entidad);
    }
    
    @GET
    public List<ReservaDTO> getReservas() {
        return listEntity2DetailDTO(logica.findAll());
    }
    
    public List<ReservaDTO> listEntity2DetailDTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
           list.add(new ReservaDTO(entity));
        }
        return list;
    }
}
