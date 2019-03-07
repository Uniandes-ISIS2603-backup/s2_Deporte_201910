
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.AmistosoLogic;
import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.AmistosoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Nicolas De la Hoz
 */
@RunWith(Arquillian.class)
public class AmistosoLogicTest {
    
    @Inject
    public AmistosoLogic amistosoLogic;
    
    @PersistenceContext
    public EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AmistosoEntity.class.getPackage())
                .addPackage(AmistosoLogic.class.getPackage())
                .addPackage(AmistosoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     * Lista de datos que se usaran en las pruebas
     */
    public List<AmistosoEntity> data = new ArrayList<AmistosoEntity>();
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    public void clearData() {
        em.createQuery("DELETE  FROM AmistosoEntity").executeUpdate();
    }


    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AmistosoEntity entity = factory.manufacturePojo(AmistosoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Amistoso.
     */
    @Test
    public void createAmistosoTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        AmistosoEntity newEntity = factory.manufacturePojo(AmistosoEntity.class);
        AmistosoEntity result = amistosoLogic.createAmistoso(newEntity);

        Assert.assertNotNull(result);

        AmistosoEntity entity = em.find(AmistosoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
     /**
     * Prueba para consultar la lista de amistosos.
     */
    @Test
    public void getAmistososTest() {
        List<AmistosoEntity> list = amistosoLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AmistosoEntity ent : list) {
            boolean found = false;
            for (AmistosoEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un amistoso.
     */
    @Test
    public void getAmistosoTest() throws BusinessLogicException {
        AmistosoEntity entity = data.get(0);
        AmistosoEntity newEntity = amistosoLogic.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar un amistoso.
     */
    @Test
    public void updateAmistosoTest() {
        AmistosoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AmistosoEntity newEntity = factory.manufacturePojo(AmistosoEntity.class);

        newEntity.setId(entity.getId());

        amistosoLogic.update(newEntity);

        AmistosoEntity resp = em.find(AmistosoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un amistoso.
     */
    @Test
    public void deleteAmistosoTest() {
        AmistosoEntity entity = data.get(0);
        amistosoLogic.delete(entity.getId());
        AmistosoEntity deleted = em.find(AmistosoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}