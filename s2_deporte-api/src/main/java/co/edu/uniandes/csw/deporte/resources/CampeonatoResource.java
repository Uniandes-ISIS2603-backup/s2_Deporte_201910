/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.CampeonatoDTO;
import co.edu.uniandes.csw.deporte.ejb.BlogLogic;
import co.edu.uniandes.csw.deporte.ejb.CampeonatoLogic;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Camilo Garcia
 */
@Path("campeonato")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped

public class CampeonatoResource {
    public static final Logger LOGGER = Logger.getLogger(CampeonatoResource.class.getName());
    
    @Inject
    public CampeonatoLogic campeonatoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.


    @POST
    public CampeonatoDTO createCampeonato(CampeonatoDTO pCampeonato)throws BusinessLogicException
    {
        CampeonatoDTO nuevoCampeonatoDTO = new CampeonatoDTO(campeonatoLogic.createCampeonato(pCampeonato.toEntity()));
        return nuevoCampeonatoDTO;
    }

    @GET
    @Path("{campeonatoId: \\d+}")
    public CampeonatoDTO getCampeonato(@PathParam("campeonatoId") Long campeonatoId)
    {
        CampeonatoEntity campeonatoEntity = campeonatoLogic.getCampeonato(campeonatoId);
        if(campeonatoEntity == null)
        {
             throw new WebApplicationException("El recurso /campeonato/" + campeonatoId + " no existe.", 404);
        }
        CampeonatoDTO campeonatoDTO = new CampeonatoDTO(campeonatoEntity);
  
        return campeonatoDTO;
    }
    
    @GET
    public List<CampeonatoDTO> getBlogs() {
        List<CampeonatoDTO> listaCampeonatos = listEntity2DetailDTO(campeonatoLogic.getCampeonatos());
        return listaCampeonatos;
    }
    
    
    @PUT
    @Path("{campeonatoId: \\d+}")
    public CampeonatoDTO updateCampeonato(@PathParam("campeonatoId")Long campeonatoId, CampeonatoDTO pCampeonato)throws BusinessLogicException
    {
        pCampeonato.setId(campeonatoId);
        if(campeonatoLogic.getCampeonato(campeonatoId) == null)
        {
            throw new  WebApplicationException("El recurso /blog/" + campeonatoId + " no existe.", 404);
        }
        CampeonatoDTO camp = new CampeonatoDTO(campeonatoLogic.updateCampeonato(campeonatoId, pCampeonato.toEntity()));
        return camp;
    }
    @DELETE
    @Path("{campeonatoId: \\d+}")
    public void deleteCampeonato(@PathParam("campeonatoId") Long campeonatoId) throws BusinessLogicException
    {
     CampeonatoEntity entity = campeonatoLogic.getCampeonato(campeonatoId);
     if(entity == null)
     {
         throw new WebApplicationException("El recurso /campeonato/" + campeonatoId + " no existe.", 404);
     }
     campeonatoLogic.deleteCampeonato(campeonatoId);
    }
    
     public List<CampeonatoDTO> listEntity2DetailDTO(List<CampeonatoEntity> entityList) {
        List<CampeonatoDTO> list = new ArrayList<>();
        for (CampeonatoEntity entity : entityList) {
            list.add(new CampeonatoDTO(entity));
        }
        return list;
    }
}
