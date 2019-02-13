/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
}
