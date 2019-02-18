/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
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
public class PropietarioPersistence {
    
    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    public PropietarioEntity create(PropietarioEntity propietarioEntity) {

        em.persist(propietarioEntity);
        return propietarioEntity;
    }
    
    public PropietarioEntity find(Long propietarioId) {
        return em.find(PropietarioEntity.class, propietarioId);
    }
    
    public List<PropietarioEntity> findAll(){
        
        TypedQuery<PropietarioEntity> query = em.createQuery("select u from PropietarioEntity u", PropietarioEntity.class);
        return query.getResultList();
    }
}
