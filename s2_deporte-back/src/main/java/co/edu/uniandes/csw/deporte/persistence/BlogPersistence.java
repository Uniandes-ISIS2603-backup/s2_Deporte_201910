/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class BlogPersistence {
     private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
   /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param editorialEntity objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BlogEntity create(BlogEntity editorialEntity) {
        LOGGER.log(Level.INFO, "Creando un blog nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(editorialEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un blog nuevo");
        return editorialEntity;
    }
}
