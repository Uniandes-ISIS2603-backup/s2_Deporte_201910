/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
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
public class CampeonatoPersistence {
     private static final Logger LOGGER = Logger.getLogger(CampeonatoPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    public CampeonatoEntity create(CampeonatoEntity editorialEntity) {
        LOGGER.log(Level.INFO, "Creando un post nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(editorialEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un post nuevo");
        return editorialEntity;
    }
}
