/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PartidoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}