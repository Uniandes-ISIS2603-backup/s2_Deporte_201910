/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
public class PostPersistence implements Serializable{
     private static final Logger LOGGER = Logger.getLogger(PostPersistence.class.getName());

    @PersistenceContext(unitName = "postPU")
    protected EntityManager em;
    public PostPersistence(PostPersistence entity)
    {
        em.persist(entity);
        
    }
}
