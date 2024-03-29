/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.FranjaDTO;
import co.edu.uniandes.csw.deporte.ejb.AgendaLogic;
import co.edu.uniandes.csw.deporte.ejb.FranjaLogic;
import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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


    @Inject
    FranjaLogic logica;
    
    @Inject 
    AgendaLogic logicaA;

    @POST
    @Path("simple")
    public FranjaDTO createFranja(FranjaDTO franja) throws BusinessLogicException{
        FranjaEntity entity = franja.toEntity();
        entity = logica.create(entity);
        return new FranjaDTO(entity);
    }
    
    @POST
    @Path("multiple")
    public List<FranjaDTO> createFranjas(List<FranjaDTO> franjas) throws BusinessLogicException
    {
        List<FranjaEntity> entities = listDTO2Entity(franjas);
        List<FranjaEntity> entities1 = new ArrayList();
        for(FranjaEntity f: entities)
        {
            entities1.add(logica.create(f));
        }
        return listEntity2DetailDTO(entities1);
    }

    @GET
    @Path("{franjaId : \\d+}")
    public FranjaDTO getFranja(@PathParam("franjaId") Long id) throws BusinessLogicException {
        FranjaEntity entity = logica.find(id);
        if (entity == null) {
            throw new WebApplicationException("Franja con id: " + id + " no existe", 404);
        }
        return new FranjaDTO(entity);
    }
 
    
    @GET
    @Path("filtroAgenda/{agendaId : \\d+}")
       public List<FranjaDTO> getFranjasPorAgenda(@PathParam("agendaId") Long id){
      return listEntity2DetailDTO(logica.findFranjasPorAgenda(id));
    }
    

    @DELETE
    @Path("{franjaId : \\d+}")
    public void deleteFranja(@PathParam("franjaId") Long id) throws BusinessLogicException {
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
    
    private List<FranjaEntity> listDTO2Entity(List<FranjaDTO> dtoList) {
        List<FranjaEntity> list = new ArrayList<>();
        for (FranjaDTO dto : dtoList) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
