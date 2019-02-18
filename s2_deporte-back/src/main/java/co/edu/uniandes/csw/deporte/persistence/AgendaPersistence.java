/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
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
public class AgendaPersistence {
    
    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    public AgendaEntity create(AgendaEntity propietarioEntity) {

        em.persist(propietarioEntity);
        return propietarioEntity;
    }
    
    public AgendaEntity find(Long propietarioId) {
        return em.find(AgendaEntity.class, propietarioId);
    }
    
    public List<AgendaEntity> findAll(){
        
        TypedQuery<AgendaEntity> query = em.createQuery("select u from PropietarioEntity u", AgendaEntity.class);
        return query.getResultList();
    }
}