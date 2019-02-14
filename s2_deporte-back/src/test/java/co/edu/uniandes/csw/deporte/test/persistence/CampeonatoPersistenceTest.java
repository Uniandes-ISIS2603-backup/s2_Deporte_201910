/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
import co.edu.uniandes.csw.deporte.persistence.CampeonatoPersistence;
import co.edu.uniandes.csw.deporte.persistence.PostPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)

public class CampeonatoPersistenceTest {
    @Inject
    CampeonatoPersistence ep;
    @PersistenceContext
    private EntityManager em;
    @Deployment
    public static JavaArchive createDeployment()
    {
       return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CampeonatoEntity.class.getPackage())
                .addPackage(CampeonatoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");    
    }
    @Test
    public void createPostTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CampeonatoEntity newEntity;
        newEntity = factory.manufacturePojo(CampeonatoEntity.class);
        CampeonatoEntity result = ep.create(newEntity);
        
        Assert.assertNotNull(result);
        
        CampeonatoEntity entity = em.find(CampeonatoEntity.class, result.getPuntos());
       
            }
}
