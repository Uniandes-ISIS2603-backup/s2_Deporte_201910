/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.PostEntity;
import java.io.Serializable;
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
public class PostPersistence implements Serializable{
     private static final Logger LOGGER = Logger.getLogger(PostPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    public PostEntity create(PostEntity postEntity)
    {
        LOGGER.log(Level.INFO, "Creando un post nuevo");
        em.persist(postEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un post nuevo");
        return postEntity;
    }   
}
