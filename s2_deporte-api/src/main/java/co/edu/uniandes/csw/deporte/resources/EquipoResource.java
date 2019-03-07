/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.EquipoDTO;
import co.edu.uniandes.csw.deporte.dtos.EquipoDetailDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    @POST
    public EquipoDTO createPropietario(EquipoDTO equipo){
        
        return equipo;
    }
    @GET
    public EquipoDetailDTO getEquipo(@PathParam("id") Long id) {
        return null;
    }
    @GET
    public List<EquipoDetailDTO> getEquipos() {
        return null;
    }
    @PUT
    public EquipoDetailDTO updateEquipo(@PathParam("id") Long id)
    {
        return null;
    }
    @DELETE
    public void deleteEquipo(@PathParam("id") Long id)
    {
        
    }
}
