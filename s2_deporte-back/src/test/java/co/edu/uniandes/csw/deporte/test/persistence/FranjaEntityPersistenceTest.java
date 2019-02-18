/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import co.edu.uniandes.csw.deporte.persistence.FranjaPersistence;
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
 * @author Santiago Barbosa
 */

@RunWith(Arquillian.class)
public class FranjaEntityPersistenceTest {
    
    @Inject
    private FranjaPersistence fp;
    
    @PersistenceContext
    private EntityManager em; 
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaEntity.class.getPackage())
                .addPackage(FranjaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createAgendaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        
        
        FranjaEntity newFranjaEntity = factory.manufacturePojo(FranjaEntity.class); 
        FranjaEntity fe = fp.create(newFranjaEntity);
        
        Assert.assertNotNull(fe);
        
        FranjaEntity entity = em.find(FranjaEntity.class, fe.getId());
        
        Assert.assertEquals(newFranjaEntity.getDuracionHoras(), entity.getDuracionHoras());
       
    }
    
}
