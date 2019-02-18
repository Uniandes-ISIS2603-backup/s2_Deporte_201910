/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import java.util.List;
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
    
    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    public FranjaEntity create (FranjaEntity franjaEntity){
        em.persist(franjaEntity);
        return franjaEntity;
    }
    
    public FranjaEntity find(Long franjasEntity){
        return em.find(FranjaEntity.class, franjasEntity);
    }
    
    public List<FranjaEntity> findAll(){
        TypedQuery<FranjaEntity> query = em.createQuery("select u from FranjaEntity u", FranjaEntity.class);
        return query.getResultList();
    }
    
}
