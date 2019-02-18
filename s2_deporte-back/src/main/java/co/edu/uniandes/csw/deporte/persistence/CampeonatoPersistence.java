/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
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
public class CampeonatoPersistence {
     private static final Logger LOGGER = Logger.getLogger(CampeonatoPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    public CampeonatoEntity create(CampeonatoEntity campeonatoEntity) {
        LOGGER.log(Level.INFO, "Creando un post nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(campeonatoEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un post nuevo");
        return campeonatoEntity;
    }
    
    public List<CampeonatoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los campeonatos");
        // Se crea un query para buscar todas las editoriales en la base de datos.
        TypedQuery query = em.createQuery("select u from CampeonatoEntity u", CampeonatoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de editoriales.
        return query.getResultList();
    }
    
    public CampeonatoEntity find(Long blogId) {
        LOGGER.log(Level.INFO, "Consultando campeonato con id={0}", blogId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CampeonatoEntity.class, blogId);
    }
   
     public CampeonatoEntity update(CampeonatoEntity campeonatoEntity) {
        LOGGER.log(Level.INFO, "Actualizando campeonato con id = {0}", campeonatoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el campeonato con id = {0}", campeonatoEntity.getId());
        return em.merge(campeonatoEntity);
    }
     
      public void delete(Long campeonatoId) {
        LOGGER.log(Level.INFO, "Borrando editorial con id = {0}", campeonatoId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        CampeonatoEntity entity = em.find(CampeonatoEntity.class, campeonatoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la editorial con id = {0}", campeonatoId);
    }
}
