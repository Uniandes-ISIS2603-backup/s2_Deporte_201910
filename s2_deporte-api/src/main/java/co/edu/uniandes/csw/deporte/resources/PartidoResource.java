/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PartidoDTO;
import co.edu.uniandes.csw.deporte.dtos.PartidoDetailDTO;
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
@Path("partidos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PartidoResource 
{
    private static final Logger LOGGER = Logger.getLogger(PartidoResource.class.getName());
    @POST
    public PartidoDTO createPropietario(PartidoDTO partido){
        
        return partido;
    }
    @GET
    public PartidoDetailDTO getPartido(@PathParam("id") Long id) {
        return null;
    }
    @GET
    public List<PartidoDetailDTO> getPartidos() {
        return null;
    }
    @PUT
    public PartidoDetailDTO updatePartido(@PathParam("id") Long id)
    {
        return null;
    }
    @DELETE
    public void deletePartido(@PathParam("id") Long id)
    {
        
    }
}
