/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Camilo Garcia
 */
@Stateless
public class CampeonatoPersistence {
     public static final Logger LOGGER = Logger.getLogger(CampeonatoPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    public CampeonatoEntity create(CampeonatoEntity campeonatoEntity) {
        LOGGER.log(Level.INFO, "Creando un campeonato nuevo");

        em.persist(campeonatoEntity);
        
        LOGGER.log(Level.INFO, "Saliendo de crear un campeonato nuevo");
        return campeonatoEntity;
    }
    
    public List<CampeonatoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los campeonatos");
        TypedQuery query = em.createQuery("select u from CampeonatoEntity u", CampeonatoEntity.class);
        return query.getResultList();
    }
    
    public CampeonatoEntity find(Long campeonatoId) {
        LOGGER.log(Level.INFO, "Consultando campeonato con id={0}", campeonatoId);

        return em.find(CampeonatoEntity.class, campeonatoId);
    }
   
     public CampeonatoEntity update(CampeonatoEntity campeonatoEntity) {
        LOGGER.log(Level.INFO, "Actualizando campeonato con id = {0}", campeonatoEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar el campeonato con id = {0}", campeonatoEntity.getId());
        return em.merge(campeonatoEntity);
    }
     
      public void delete(Long campeonatoId) {
        LOGGER.log(Level.INFO, "Borrando editorial con id = {0}", campeonatoId);
        CampeonatoEntity entity = em.find(CampeonatoEntity.class, campeonatoId);
               em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la editorial con id = {0}", campeonatoId);
    }
      
      public CampeonatoEntity findByName(String pNombre)
    {
        LOGGER.log(Level.INFO, "Consultando campeonatos por nombre ", pNombre);
        TypedQuery query = em.createQuery("Select e From CampeonatoEntity e where e.nombre = :nombre", CampeonatoEntity.class);
        
        query = query.setParameter("nombre", pNombre);
        
        List<CampeonatoEntity> sameNombre = query.getResultList();
        CampeonatoEntity result;
        if(sameNombre == null)
        {
            result = null;
        }
        else if(sameNombre.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar campeonatos por nombre ", pNombre);
        return result;
    }

}
