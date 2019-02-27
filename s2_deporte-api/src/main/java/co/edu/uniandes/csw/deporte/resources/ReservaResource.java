/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.ReservaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    
    @POST
    public ReservaDTO createReseva(ReservaDTO reserva){
        return reserva;
    }
    
    @PUT
    public ReservaDTO modifyReseva(ReservaDTO reserva){
        return reserva;
    }
    
    @DELETE
    public ReservaDTO deleteReserva(ReservaDTO reserva){
        return reserva;
    }
    
}
