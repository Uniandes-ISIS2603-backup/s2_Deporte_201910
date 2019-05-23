/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PartidoDTO;
import co.edu.uniandes.csw.deporte.dtos.PartidoDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.PartidoLogic;
import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
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

/**
 *
 * @author estudiante
 */
@Path("partidos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PartidoResource 
{
    
    @Inject
    private PartidoLogic partidoLogic;
    
    @POST
    public PartidoDTO createPropietario(PartidoDTO partido) throws BusinessLogicException{
        
        return new PartidoDTO(partidoLogic.createPartido(partido.toEntity()));
    }
    
    @GET
    @Path("{partidoId: \\d+}")
    public PartidoDetailDTO getPartido(@PathParam("id") Long id) 
    {
        PartidoEntity p = partidoLogic.getPartido(id);
        if(p==null)
        {
            throw new WebApplicationException("El recurso /partido/"+id+"no existe.",404);
        }
        return new PartidoDetailDTO(p);
    }
    
    @GET
    public List<PartidoDetailDTO> getPartidos() 
    {
        List<PartidoDetailDTO> res = new ArrayList<>();
        for(PartidoEntity p : partidoLogic.getPartidos())
        {
            res.add(new PartidoDetailDTO(p));
        }
        return res;
    }
    @PUT
    @Path("{partidoId: \\d+}")
    public PartidoDetailDTO updatePartido(@PathParam("id") Long id, PartidoDetailDTO partido) throws BusinessLogicException
    {
        if(partidoLogic.getPartido(id)== null)
        {
             throw new WebApplicationException("El recurso /partido/"+id+"no existe.",404);
        }
        return new PartidoDetailDTO(partidoLogic.updatePartido(id, partido.toEntity()));
    }
    
    
    @DELETE
    @Path("{partidoId: \\d+}")
    public void deletePartido(@PathParam("id") Long id) throws BusinessLogicException
    {
        if(partidoLogic.getPartido(id)== null)
        {
             throw new WebApplicationException("El recurso /partido/"+id+"no existe.",404);
        }
        partidoLogic.deletePartido(id);
    }
}
