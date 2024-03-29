/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.EntrenamientoEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Nicolas De la Hoz
 */
@Stateless
public class EntrenamientoPersistence {
    public static final Logger LOGGER=Logger.getLogger(EntrenamientoPersistence.class.getName());
    
    @PersistenceContext (unitName="deportePU")
    protected EntityManager em;
    
    
    public EntrenamientoEntity create(EntrenamientoEntity entidad){
        em.persist(entidad);
        return entidad;
    }
     public EntrenamientoEntity find(Long id){
        return em.find(EntrenamientoEntity.class, id);
    }
     
     public List<EntrenamientoEntity> findAll(){
        Query query = em.createQuery("SELECT u FROM EntrenamientoEntity u");
        return query.getResultList();
    }
     
     public EntrenamientoEntity update(EntrenamientoEntity entidad){
         return em.merge(entidad);
    }
     
    public void delete(Long id){
        em.remove(em.find(EntrenamientoEntity.class, id));
    }
}
