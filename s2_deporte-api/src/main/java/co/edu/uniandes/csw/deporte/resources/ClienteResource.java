/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.ClienteDTO;

import co.edu.uniandes.csw.deporte.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.deporte.dtos.ClienteDTO;
import co.edu.uniandes.csw.deporte.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.ClienteLogic;
import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
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
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ClienteResource 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    @Inject
    private ClienteLogic clienteLogic;
    
    @POST
    public ClienteDTO createPropietario(ClienteDTO cliente) throws BusinessLogicException{
        
        return new ClienteDTO(clienteLogic.createCliente(cliente.toEntity()));
    }
    @GET
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("id") Long id) 
    {
        ClienteEntity p = clienteLogic.getCliente(id);
        if(p==null)
        {
            throw new WebApplicationException("El recurso /cliente/"+id+"no existe.",404);
        }
        return new ClienteDetailDTO(p);
    }
    @GET
    public List<ClienteDetailDTO> getClientes() 
    {
        List<ClienteDetailDTO> res = new ArrayList<>();
        for(ClienteEntity p : clienteLogic.getClientes())
        {
            res.add(new ClienteDetailDTO(p));
        }
        return res;
    }
    @PUT
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("id") Long id, ClienteDetailDTO cliente) throws BusinessLogicException
    {
        if(clienteLogic.getCliente(id)== null)
        {
             throw new WebApplicationException("El recurso /cliente/"+id+"no existe.",404);
        }
        return new ClienteDetailDTO(clienteLogic.updateCliente(id, cliente.toEntity()));
    }
    
    
    @DELETE
    @Path("{clienteId: \\d+}")
    public void deleteCliente(@PathParam("id") Long id) throws BusinessLogicException
    {
        if(clienteLogic.getCliente(id)== null)
        {
             throw new WebApplicationException("El recurso /cliente/"+id+"no existe.",404);
        }
        clienteLogic.deleteCliente(id);
    }
}
