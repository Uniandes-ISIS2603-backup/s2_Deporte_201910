/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import java.util.List;
import java.util.logging.Level;
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
    
     private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(AgendaPersistence.class.getName());

    
    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    public AgendaEntity create(AgendaEntity agendaEntity) {

        em.persist(agendaEntity);
        return agendaEntity;
    }
    
    public AgendaEntity find(Long agendaId) {
        
        LOGGER.log(Level.INFO, "Consultando la agenda con id = (0)", agendaId);
        return em.find(AgendaEntity.class, agendaId);
    }
    
    public List<AgendaEntity> findAll(){
        
        LOGGER.log(Level.INFO, "Consultando todas las agendas");
        TypedQuery<AgendaEntity> query = em.createQuery("select u from AgendaEntity u", AgendaEntity.class);
        return query.getResultList();
    }

    public void delete(Long agendaId) {
        LOGGER.log(Level.INFO, "Borrando la agenda con id = (0)", agendaId);
        
        AgendaEntity agendaEntity = em.find(AgendaEntity.class, agendaId);
        em.remove(agendaEntity);
    }

    public AgendaEntity update(AgendaEntity agendaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la agenda con id={0}", agendaEntity.getId());
        
        return em.merge(agendaEntity);
    }
}