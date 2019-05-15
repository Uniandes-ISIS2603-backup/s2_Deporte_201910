/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.ReservaEntity;
import java.util.Collection;
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
public class ReservaPersistence {
    
    public static final Logger LOGGER=Logger.getLogger(ReservaPersistence.class.getName());
    
    @PersistenceContext (unitName="deportePU")
    protected EntityManager em;
    
    
    public ReservaEntity create(ReservaEntity entidad){
        em.persist(entidad);
        return entidad;
    }
    
     public ReservaEntity find(Long id){
        ReservaEntity reserva=em.find(ReservaEntity.class, id);
        return reserva;
    }
     
     public List<ReservaEntity> findAll(){
        Query query = em.createQuery("SELECT u FROM ReservaEntity u", ReservaEntity.class);
        return query.getResultList();
    }
     
     public ReservaEntity update(ReservaEntity entidad){
         return em.merge(entidad);
    }
    public void delete(Long id){
        em.remove(em.find(ReservaEntity.class, id));
    }
}
