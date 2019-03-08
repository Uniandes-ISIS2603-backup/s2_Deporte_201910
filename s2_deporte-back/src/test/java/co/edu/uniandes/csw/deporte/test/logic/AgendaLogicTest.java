/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.AgendaLogic;
import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.AgendaPersistence;
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
 * @author Santiago Barbosa
 */
@RunWith(Arquillian.class)
public class AgendaLogicTest {
    
    @Inject
    public AgendaLogic agendaLogic;
    
    @PersistenceContext
    public EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AgendaEntity.class.getPackage())
                .addPackage(AgendaLogic.class.getPackage())
                .addPackage(AgendaPersistence.class.getPackage())
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
    public List<AgendaEntity> data = new ArrayList<AgendaEntity>();
    
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
        em.createQuery("DELETE  FROM AgendaEntity").executeUpdate();
    }


    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AgendaEntity entity = factory.manufacturePojo(AgendaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Agenda.
     */
    @Test
    public void createAgendaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        AgendaEntity newEntity = factory.manufacturePojo(AgendaEntity.class);
        AgendaEntity result = agendaLogic.create(newEntity);

        Assert.assertNotNull(result);

        AgendaEntity entity = em.find(AgendaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
     /**
     * Prueba para consultar la lista de agendas.
     */
    @Test
    public void getAgendasTest() {
        List<AgendaEntity> list = agendaLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AgendaEntity ent : list) {
            boolean found = false;
            for (AgendaEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una agenda.
     */
    @Test
    public void getAmistosoTest() throws BusinessLogicException {
        AgendaEntity entity = data.get(0);
        AgendaEntity newEntity = agendaLogic.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar una agenda.
     */
    @Test
    public void updateAgendaTest() {
        AgendaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AgendaEntity newEntity = factory.manufacturePojo(AgendaEntity.class);

        newEntity.setId(entity.getId());

        agendaLogic.update(newEntity);

        AgendaEntity resp = em.find(AgendaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar una agenda.
     */
    @Test
    public void deleteAmistosoTest() {
        AgendaEntity entity = data.get(0);
        agendaLogic.delete(entity.getId());
        AgendaEntity deleted = em.find(AgendaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
