/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
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
public class AmistosoPersistence {
    private static final Logger LOGGER=Logger.getLogger(AmistosoPersistence.class.getName());
    
    @PersistenceContext (unitName="deportePU")
    protected EntityManager em;
    
    
    public AmistosoEntity create(AmistosoEntity entidad){
        em.persist(entidad);
        return entidad;
    }
    
    public AmistosoEntity find(Long id){
        AmistosoEntity amistoso=em.find(AmistosoEntity.class, id);
        return amistoso;
    }
     
    public List<AmistosoEntity> findAll(){
        Query query = em.createQuery("SELECT u FROM AmistosoEntity u");
        return  query.getResultList();
    }
     
     public AmistosoEntity update(AmistosoEntity entidad){
         return em.merge(entidad);
    }
     
    public void delete(Long id){
        em.remove(em.find(AmistosoEntity.class, id));
    }
}
