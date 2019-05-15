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

/**
 *
 * @author Nicolas De la Hoz
 */

@Path ("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {
    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());
    
        
    @Inject
    ReservaLogic logica;
    
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws BusinessLogicException{
        ReservaEntity reservaEntity=reserva.toEntity();
        reservaEntity= logica.createReserva(reservaEntity);
        return new  ReservaDTO(reservaEntity);
    }
    @PUT
    public ReservaDTO modifyReserva(ReservaDTO reserva){
        return reserva;
    }
    
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteEntrenamiento(@PathParam("reservaId")Long reservaId) throws BusinessLogicException {
        ReservaEntity entidad=logica.find(reservaId);
        if(entidad==null){
            throw new WebApplicationException("Entrenamiento con id: " + reservaId + " no existe", 404);
        }
        logica.delete(reservaId);
    }
    
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDTO getEntrenamiento(@PathParam("reservaId")Long reservaId) throws BusinessLogicException{
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
