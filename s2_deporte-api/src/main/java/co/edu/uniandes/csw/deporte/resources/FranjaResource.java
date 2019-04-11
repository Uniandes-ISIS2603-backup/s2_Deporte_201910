/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.FranjaDTO;
import co.edu.uniandes.csw.deporte.ejb.FranjaLogic;
import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Santiago Barbosa
 */
@Path("franjas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FranjaResource {

    private static final Logger LOGGER = Logger.getLogger(FranjaResource.class.getName());

    @Inject
    FranjaLogic logica;

    @POST
    public FranjaDTO createFranja(FranjaDTO franja) throws BusinessLogicException {
        FranjaEntity entity = franja.toEntity();
        entity = logica.create(entity);
        return new FranjaDTO(entity);
    }

    @GET
    @Path("{franjaId : \\d+}")
    public FranjaDTO getFranja(@PathParam("franjaId") Long id) throws WebApplicationException, BusinessLogicException {
        FranjaEntity entity = logica.find(id);
        if (entity == null) {
            throw new WebApplicationException("Franja con id: " + id + " no existe", 404);
        }
        return new FranjaDTO(entity);
    }
  
    
    @GET
    @Path("filtroAgenda/{agendaId : \\d+}")
       public List<FranjaDTO> getAgendasPorCancha(@PathParam("agendaId") Long id){
       List<FranjaDTO> listaFranjas = listEntity2DetailDTO(logica.findFranjasPorAgenda(id));
        return listaFranjas;
    }
    

    @DELETE
    @Path("{franjaId : \\d+}")
    public void deleteFranja(@PathParam("franjaId") Long id) throws WebApplicationException, BusinessLogicException {
        FranjaEntity entity = logica.find(id);
        if (entity == null) {
            throw new WebApplicationException("Franja con id: " + id + " no existe", 404);
        }
        logica.delete(id);
    }
    
    private List<FranjaDTO> listEntity2DetailDTO(List<FranjaEntity> entityList) {
        List<FranjaDTO> list = new ArrayList<>();
        for (FranjaEntity entity : entityList) {
            list.add(new FranjaDTO(entity));
        }
        return list;
    }
}
