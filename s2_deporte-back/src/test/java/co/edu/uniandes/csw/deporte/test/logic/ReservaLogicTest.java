/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.ReservaLogic;
import co.edu.uniandes.csw.deporte.entities.ReservaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class ReservaLogicTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
   
    /**
     * Inyección de la dependencia a la clase AmistosoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    public ReservaLogic reservaLogic;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    public EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     * Lista de datos que se usaran en las pruebas
     */
    public List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
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
        em.createQuery("DELETE  FROM ReservaEntity").executeUpdate();
    }


    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Reserva.
     */
    @Test
    public void createReservaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result;
        newEntity.setFechaInicio(new Date(10000));
        newEntity.setFechaFin(new Date(2000000));
        result = reservaLogic.createReserva(newEntity);

        Assert.assertNotNull(result);
        
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        
    }
    
    /**
     * Prueba para crear un Reserva.
     */
    @Test (expected = BusinessLogicException.class)
    public void createReservaFailTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result;
        newEntity.setFechaInicio(new Date(20000000));
        newEntity.setFechaFin(new Date(1000));
        result = reservaLogic.createReserva(newEntity);
        
    }
    
    /**
     * Prueba para consultar la lista de Reservas.
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = reservaLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity ent : list) {
            boolean found = false;
            for (ReservaEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Reserva.
     */
    @Test
    public void getReservaTest() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        ReservaEntity newEntity = reservaLogic.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar un Reserva.
     */
    @Test
    public void updateReservaTest() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setFechaInicio(new Date(10000));
        newEntity.setFechaFin(new Date(2000000));
        
        reservaLogic.update(newEntity);

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para actualizar un Reserva.
     */
    @Test(expected = BusinessLogicException.class)
    public void updateReservaFailTest() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setFechaInicio(new Date(20000000));
        newEntity.setFechaFin(new Date(1000));
        
        reservaLogic.update(newEntity);

    }

    /**
     * Prueba para eliminar un Reserva.
     */
    @Test
    public void deleteReservaTest() {
        ReservaEntity entity = data.get(0);
        reservaLogic.delete(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
   
}
