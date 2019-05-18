/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.EquipoDTO;
import co.edu.uniandes.csw.deporte.dtos.EquipoDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.EquipoLogic;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
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
@Path("equipos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoResource 
{
    private static final Logger LOGGER = Logger.getLogger(EquipoResource.class.getName());
    @Inject
    private EquipoLogic equipoLogic;
    
    private String WAE="El recurso /cliente/";
    private String NE="no existe.";
    @POST
    public EquipoDTO createPropietario(EquipoDTO equipo) throws BusinessLogicException{
        
        return new EquipoDTO(equipoLogic.createEquipo(equipo.toEntity()));
    }
    @GET
    @Path("{equipoId: \\d+}")
    public EquipoDetailDTO getEquipo(@PathParam("equipoId") Long id) 
    {
        EquipoEntity p = equipoLogic.getEquipo(id);
        if(p==null)
        {
            throw new WebApplicationException(WAE+id+NE,404);
        }
        return new EquipoDetailDTO(p);
    }
    @GET
    public List<EquipoDetailDTO> getEquipos() 
    {
        List<EquipoDetailDTO> res = new ArrayList<>();
        for(EquipoEntity p : equipoLogic.getEquipos())
        {
            res.add(new EquipoDetailDTO(p));
        }
        return res;
    }
    @PUT
    @Path("{equipoId: \\d+}")
    public EquipoDetailDTO updateEquipo(@PathParam("equipoId") Long id, EquipoDetailDTO equipo) throws BusinessLogicException
    {
        if(equipoLogic.getEquipo(id)== null)
        {
             throw new WebApplicationException(WAE+id+NE,404);
        }
        return new EquipoDetailDTO(equipoLogic.updateEquipo(id, equipo.toEntity()));
    }
    
    
    @DELETE
    @Path("{equipoId: \\d+}")
    public void deleteEquipo(@PathParam("equipoId") Long id) throws BusinessLogicException
    {
        if(equipoLogic.getEquipo(id)== null)
        {
             throw new WebApplicationException(WAE+id+NE,404);
        }
        equipoLogic.deleteEquipo(id);
    }
}
