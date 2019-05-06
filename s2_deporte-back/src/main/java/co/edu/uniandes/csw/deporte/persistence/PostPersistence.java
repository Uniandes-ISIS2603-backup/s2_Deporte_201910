/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.PostEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
*
* @author Juan Camilo Garcia
*/
@Stateless
public class PostPersistence{
 public static final Logger LOGGER = Logger.getLogger(PostPersistence.class.getName());

@PersistenceContext(unitName = "deportePU")
protected EntityManager em;

/**
 * Método para persisitir la entidad en la base de datos.
 *
 * @param postEntity objeto editorial que se creará en la base de datos
 * @return devuelve la entidad creada con un id dado por la base de datos.
 */
public PostEntity create(PostEntity postEntity) {
    LOGGER.log(Level.INFO, "Creando un post nuevo");
    /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
    Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
     */
    if(postEntity != null)
        em.persist(postEntity);
   
    LOGGER.log(Level.INFO, "Saliendo de crear un post nuevo");
    return postEntity;
}

public List<PostEntity> findAll() {
    LOGGER.log(Level.INFO, "Consultando todos los posts");
    // Se crea un query para buscar todas las editoriales en la base de datos.
    TypedQuery query = em.createQuery("select u from PostEntity u", PostEntity.class);
    return query.getResultList();
}

public PostEntity find(Long postId) {
    LOGGER.log(Level.INFO, "Consultando post con id={0}", postId);
    /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
    el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
    Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
     */
    return em.find(PostEntity.class, postId);
}

public PostEntity update(PostEntity postlEntity) {
    LOGGER.log(Level.INFO, "Actualizando post con id = {0}", postlEntity.getId());
    /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
    la editorial con los cambios, esto es similar a 
    "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
     */
    LOGGER.log(Level.INFO, "Saliendo de actualizar  el post con id = {0}", postlEntity.getId());
    return em.merge(postlEntity);
}

public void delete(Long postId) {
    LOGGER.log(Level.INFO, "Borrando editorial con id = {0}", postId);
    // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
    PostEntity entity = em.find(PostEntity.class, postId);
    /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
     EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
     Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
    em.remove(entity);
    LOGGER.log(Level.INFO, "Saliendo de borrar el post con id = {0}", postId);
}


}