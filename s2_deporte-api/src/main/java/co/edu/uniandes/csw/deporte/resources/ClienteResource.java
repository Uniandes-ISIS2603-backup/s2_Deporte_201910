/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.ClienteDTO;

import co.edu.uniandes.csw.deporte.dtos.ClienteDetailDTO;
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
@Path("cliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ClienteResource 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    @POST
    public ClienteDTO createPropietario(ClienteDTO cliente){
        
        return cliente;
    }
    @GET
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("id") Long id) {
        return null;
    }
    @GET
    public List<ClienteDetailDTO> getClientes() {
        return null;
    }
    @PUT
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("id") Long id)
    {
        return null;
    }
    @DELETE
    @Path("{clienteId: \\d+}")
    public void deleteCliente(@PathParam("id") Long id)
    {
        
    }
}
