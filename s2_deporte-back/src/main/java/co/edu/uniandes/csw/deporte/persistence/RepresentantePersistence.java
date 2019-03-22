/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.RepresentanteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class RepresentantePersistence 
{
    public static final Logger LOGGER = Logger.getLogger(RepresentantePersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param representanteEntity objeto representante que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public RepresentanteEntity create(RepresentanteEntity representanteEntity) {
        LOGGER.log(Level.INFO, "Creando un autor nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la representante en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(representanteEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return representanteEntity;
    }

    /**
     * Devuelve todas las representantees de la base de datos.
     *
     * @return una lista con todas las representantees que encuentre en la base de
     * datos, "select u from RepresentanteEntity u" es como un "select * from
     * RepresentanteEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<RepresentanteEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        // Se crea un query para buscar todas las representantees en la base de datos.
        TypedQuery query = em.createQuery("select u from RepresentanteEntity u", RepresentanteEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de representantees.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna representante con el id que se envía de argumento
     *
     * @param representantesId: id correspondiente a la representante buscada.
     * @return un representante.
     */
    public RepresentanteEntity find(Long representantesId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", representantesId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from RepresentanteEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(RepresentanteEntity.class, representantesId);
    }

    /**
     * Actualiza una representante.
     *
     * @param representanteEntity: la representante que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una representante con los cambios aplicados.
     */
    public RepresentanteEntity update(RepresentanteEntity representanteEntity) {
        LOGGER.log(Level.INFO, "Actualizando el representante con id={0}", representanteEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la representante con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(representanteEntity);
    }

    /**
     * Borra una representante de la base de datos recibiendo como argumento el id de
     * la representante
     *
     * @param representantesId: id correspondiente a la representante a borrar.
     */
    public void delete(Long representantesId) {

        LOGGER.log(Level.INFO, "Borrando el representante con id={0}", representantesId);
        // Se hace uso de mismo método que esta explicado en public RepresentanteEntity find(Long id) para obtener la representante a borrar.
        RepresentanteEntity representanteEntity = em.find(RepresentanteEntity.class, representantesId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from RepresentanteEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(representanteEntity);
    }
}
