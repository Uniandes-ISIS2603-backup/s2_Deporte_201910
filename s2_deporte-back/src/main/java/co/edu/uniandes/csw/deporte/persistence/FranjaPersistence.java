/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Santiago Barbosa
 */

@Stateless
public class FranjaPersistence {
    
    public static final java.util.logging.Logger LOGGER = Logger.getLogger(FranjaPersistence.class.getName());
    
    @PersistenceContext (unitName="deportePU")
    protected EntityManager em;
    
    public FranjaEntity create (FranjaEntity franjaEntity){
        em.persist(franjaEntity);
        return franjaEntity;
    }
    
    public FranjaEntity find(Long franjaId){
        
        LOGGER.log(Level.INFO, "Consultando la franja con id = {0}", franjaId);
        return em.find(FranjaEntity.class, franjaId);
    }
    
    public List<FranjaEntity> findAll(){
        
        LOGGER.log(Level.INFO, "Consultando todas las franjas");
        TypedQuery<FranjaEntity> query = em.createQuery("SELECT u FROM FranjaEntity u", FranjaEntity.class);
        return query.getResultList();
    }

    public List<FranjaEntity> findFranjasPorAgenda(Long id){
        
        LOGGER.log(Level.INFO, "Consultando todas las agendas de la cancha con id: {0}", id);
        
        TypedQuery<FranjaEntity> query = em.createQuery("select e from FranjaEntity e where e.agenda.id =:franjaid" , FranjaEntity.class);
        query.setParameter("franjaid", id);
        return query.getResultList();
    }
    
    public void delete(Long franjaId) {
        LOGGER.log(Level.INFO, "Borrando la franja con id = {0}", franjaId);
        
        FranjaEntity franjaEntity = em.find(FranjaEntity.class, franjaId);
        em.remove(franjaEntity);
    }

    public FranjaEntity update(FranjaEntity franjaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la frnja con id={0}", franjaEntity.getId());
        
        return em.merge(franjaEntity);
    }
}
