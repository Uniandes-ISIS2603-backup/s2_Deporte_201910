/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.ClienteEntity;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class ClientePersistence 
{
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    /**
     * Crea un cliente en la base de datos
     *
     * @param clienteEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando un cliente nuevo");
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Cliente creado");
        return clienteEntity;
    }

    /**
     * Devuelve todos los clientes de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de datos
     */
    public List<ClienteEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay alguna cliente con el id que se envía de argumento
     *
     * @param clientessId: id correspondiente al cliente buscada.
     * @return un cliente.
     */
    public ClienteEntity find(Long clientesId) {
        LOGGER.log(Level.INFO, "Consultando el cliente con id={0}", clientesId);
        return em.find(ClienteEntity.class, clientesId);
    }

    /**
     * Actualiza un cliente.
     *
     * @param clienteEntity: el cliente que viene con los nuevos cambios
     * @return un cliente con los cambios aplicados.
     */
    public ClienteEntity update(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente con id={0}", clienteEntity.getId());
        return em.merge(clienteEntity);
    }

    /**
     * Borra un cliente de la base de datos recibiendo como argumento el id del cliente
     * @param clientesId: id correspondiente al cliente a borrar.
     */
    public void delete(Long clientesId) {

        LOGGER.log(Level.INFO, "Borrando el cliente con id={0}", clientesId);
        ClienteEntity clienteEntity = em.find(ClienteEntity.class, clientesId);
        em.remove(clienteEntity);
    }
    
}
