/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Poch
 */
@Stateless
public class ClienteLogic 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;
    /**
     * crea al cliente y lo persiste
     * @param clienteEntity el cliente que se va a crear
     * @return el cliente que se creo
     * @throws BusinessLogicException: si el nombre es null
     *                                 si el cliente ya existe                                 
     */
    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Se empieza a crear el cliente");
        if(clienteEntity.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre del cliente es null");
        }
        if(getCliente(clienteEntity.getId())!=null)
        {
            throw new BusinessLogicException("El cliente que se va a crear ya existe");
        }
        if(this.verificacionDeEquipos(clienteEntity.getEquipos()))
        {
            throw new BusinessLogicException("El cliente tiene equipos repetidos");
        }
        clientePersistence.create(clienteEntity);
        LOGGER.log(Level.INFO, "Se termino de crear el cliente");
        return clienteEntity;
    }
    /**
     * busca un cliente en la base de datos
     * @param clienteId el id del cliente a buscar
     * @return el resultado de la busqueda
     */
    public ClienteEntity getCliente(Long clienteId)
    {
        LOGGER.log(Level.INFO, "se empieza la busqueda de cliente con id={0}", clienteId);
        ClienteEntity res = clientePersistence.find(clienteId);
        if(res==null)
        {
            LOGGER.log(Level.INFO,"el cliente no existe en la base de datos", clienteId);
        }
        LOGGER.log(Level.INFO,"Se termina el proceso de busqueda para el cliente con id=", clienteId);
        return res;
    }
    /**
     * devuelve todos lo clientes en la base de datos
     * @return todos los clientes
     */
    public List<ClienteEntity> getClientes()
    {
        LOGGER.log(Level.INFO,"Se va a hacer consulta de todos los clientes");
        List<ClienteEntity> res = clientePersistence.findAll();
        LOGGER.log(Level.INFO,"se termina la consulta de todos los clientes");
        return res;
    }
    /**
     * modifica la información de un clinete
     * @param clienteId el id del cliente a modificar
     * @param clienteEntity la nueva información que se le va a poner
     * @return el resultado de la modificacion
     * @throws BusinessLogicException si en los datos a modificar el nombre es null
     */
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity clienteEntity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Se empieza la modificación del cliente con id={0}", clienteId);
        if(clienteEntity.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre del cliente es null");
        }
        if(this.verificacionDeEquipos(clienteEntity.getEquipos()))
        {
            throw new BusinessLogicException("El cliente tiene equipos repetidos");
        }
        ClienteEntity res = clientePersistence.update(clienteEntity);
        LOGGER.log(Level.INFO, "Se modificó del cliente con id={0}", clienteId);
        return res;
    }
    /**
     * se elimina un cliente
     * @param clienteId el id del cliente a eliminar
     */
    public void deleteCliente(Long clienteId) 
    {
        LOGGER.log(Level.INFO, "Se empieza el proceso para eliminar el cliente con id={0}", clienteId);
        clientePersistence.delete(clienteId);        
    }
    
    public boolean verificacionDeEquipos(List<EquipoEntity> equipos)
    {
        for(int i=0; i<equipos.size()-1;i++)
        {
            EquipoEntity e = equipos.get(i);
            for(int x =i+1;x<equipos.size();x++)
            {
                if(e.getId()==equipos.get(x).getId())
                {
                    return true;
                }
            }
        }
        return false;
    }
}
