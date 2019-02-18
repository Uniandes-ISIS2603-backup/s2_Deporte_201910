/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import co.edu.uniandes.csw.deporte.persistence.AgendaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author SantiagoBarbosa
 */

@RunWith(Arquillian.class)
public class AgendaEntityPersistenceTest {
    
    @Inject
    private AgendaPersistence agendaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AgendaEntity.class.getPackage())
                .addPackage(AgendaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createAgendaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        
        
        AgendaEntity newAgendaEntity = factory.manufacturePojo(AgendaEntity.class); 
        AgendaEntity agendaEntity = agendaPersistence.create(newAgendaEntity);
        
        Assert.assertNotNull(agendaEntity);
        
        AgendaEntity entity = em.find(AgendaEntity.class, agendaEntity.getId());
       
        Assert.assertEquals(newAgendaEntity.getAnio(), entity.getAnio());
       
        Assert.assertEquals(newAgendaEntity.getMes(), entity.getMes());
    }
   
    
}
