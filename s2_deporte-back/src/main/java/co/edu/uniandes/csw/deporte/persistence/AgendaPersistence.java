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
 * @author Santiago Barbosa
 */
@Stateless
public class AgendaPersistence {

    @PersistenceContext(unitName = "deportesPU")
    protected EntityManager em;

    public AgendaEntity create(AgendaEntity agendaEntity) {
        em.persist(agendaEntity);
        return agendaEntity;
    }

    public AgendaEntity find(Long agendasId) {
        return em.find(AgendaEntity.class, agendasId);
    }
    
    public List<AgendaEntity> findAll(){
        TypedQuery<AgendaEntity> query = em.createQuery("select u from AgendaEntity u", AgendaEntity.class);
        return query.getResultList();
    }
}
