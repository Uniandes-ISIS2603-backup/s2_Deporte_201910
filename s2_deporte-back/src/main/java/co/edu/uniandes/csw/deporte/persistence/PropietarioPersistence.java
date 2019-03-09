/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
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
public class PropietarioPersistence {

    public static final Logger LOGGER = Logger.getLogger(PropietarioPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;

    /**
     * Crea un propietario en la base de datos
     *
     * @param propietarioEntity objeto propietario que se creará en la base de
     * datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PropietarioEntity create(PropietarioEntity propietarioEntity) {

        em.persist(propietarioEntity);
        return propietarioEntity;
    }

    /**
     * Busca si hay alguna propietario con el id que se envía de argumento
     *
     * @param propietarioId: id correspondiente a el propietario buscado.
     * @return un propietario.
     */
    public PropietarioEntity find(Long propietarioId) {
        return em.find(PropietarioEntity.class, propietarioId);
    }

    /**
     * Devuelve todos los propietario de la base de datos.
     *
     * @return una lista con todas los propietarios que encuentre en la base de
     * datos, "select u from PropietarioEntity u" es como un "select * from
     * PropietarioEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PropietarioEntity> findAll() {

        TypedQuery<PropietarioEntity> query = em.createQuery("select u from PropietarioEntity u", PropietarioEntity.class);
        return query.getResultList();
    }

    public PropietarioEntity findByName(String nombre) {

        TypedQuery<PropietarioEntity> query = em.createQuery("select e from PropietarioEntity e where e.nombre = :nombre", PropietarioEntity.class);

        query = query.setParameter("nombre", nombre);
        PropietarioEntity resul;
        List<PropietarioEntity> sameName = query.getResultList();
        if (sameName == null) {
            resul = null;
        } else if (sameName.isEmpty()) {
            resul = null;
        } else {
            resul = sameName.get(0);
        }
        return resul;
    }

    /**
     * Actualiza un propietario.
     *
     * @param propietarioEntity: el propietario que viene con los nuevos
     * cambios. Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso
     * del método update.
     * @return un propietario con los cambios aplicados.
     */
    public PropietarioEntity update(PropietarioEntity propietarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el propietario con id={0}", propietarioEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(propietarioEntity);
    }

    /**
     *
     * Borra una cancha de la base de datos recibiendo como argumento el id de
     * la cancha
     *
     * @param propietariosId: id correspondiente a la editorial a borrar.
     */
    public void delete(Long propietariosId) {
        LOGGER.log(Level.INFO, "Borrando propietario con id = {0}", propietariosId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        PropietarioEntity entity = em.find(PropietarioEntity.class, propietariosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la propietario con id = {0}", propietariosId);
    }
}
