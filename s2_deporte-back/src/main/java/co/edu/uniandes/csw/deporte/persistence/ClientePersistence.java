/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.ClienteEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @cliente estudiante
 */
@Stateless
public class ClientePersistence 
{
    public static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param clienteEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando un autor nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la cliente en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return clienteEntity;
    }

    /**
     * Devuelve todas las clientees de la base de datos.
     *
     * @return una lista con todas las clientees que encuentre en la base de
     * datos, "select u from ClienteEntity u" es como un "select * from
     * ClienteEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ClienteEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        // Se crea un query para buscar todas las clientees en la base de datos.
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de clientees.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna cliente con el id que se envía de argumento
     *
     * @param clientesId: id correspondiente a la cliente buscada.
     * @return un cliente.
     */
    public ClienteEntity find(Long clientesId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", clientesId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ClienteEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ClienteEntity.class, clientesId);
    }

    /**
     * Actualiza una cliente.
     *
     * @param clienteEntity: la cliente que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una cliente con los cambios aplicados.
     */
    public ClienteEntity update(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente con id={0}", clienteEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la cliente con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(clienteEntity);
    }

    /**
     * Borra una cliente de la base de datos recibiendo como argumento el id de
     * la cliente
     *
     * @param clientesId: id correspondiente a la cliente a borrar.
     */
    public void delete(Long clientesId) {

        LOGGER.log(Level.INFO, "Borrando el cliente con id={0}", clientesId);
        // Se hace uso de mismo método que esta explicado en public ClienteEntity find(Long id) para obtener la cliente a borrar.
        ClienteEntity clienteEntity = em.find(ClienteEntity.class, clientesId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from ClienteEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(clienteEntity);
    }
    
}
