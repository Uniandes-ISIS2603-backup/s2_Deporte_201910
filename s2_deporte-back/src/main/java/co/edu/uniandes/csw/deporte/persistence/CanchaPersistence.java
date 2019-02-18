/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class CanchaPersistence {

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;

    public CanchaEntity create(CanchaEntity canchaEntity) {

        em.persist(canchaEntity);
        return canchaEntity;
    }

    public CanchaEntity find(Long canchaId) {
        return em.find(CanchaEntity.class, canchaId);
    }
    
    public List<CanchaEntity> findAll(){
        
        TypedQuery<CanchaEntity> query = em.createQuery("select u from CanchaEntity u", CanchaEntity.class);
        return query.getResultList();
    }
}
