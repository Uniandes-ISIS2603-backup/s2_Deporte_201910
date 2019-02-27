/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.EntrenamientoEntity;

import co.edu.uniandes.csw.deporte.entities.EntrenamientoEntity;
import co.edu.uniandes.csw.deporte.persistence.EntrenamientoPersistence;
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
public class EntrenamientoPersistenceTest {
        
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EntrenamientoEntity.class.getPackage())
                .addPackage(EntrenamientoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
   
    /**
     * Inyección de la dependencia a la clase EntrenamientoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EntrenamientoPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     * Lista de datos que se usaran en las pruebas
     */
    private List<EntrenamientoEntity> data = new ArrayList<>();
    
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
    
    private void clearData() {
        em.createQuery("DELETE  FROM EntrenamientoEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EntrenamientoEntity entity = factory.manufacturePojo(EntrenamientoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Entrenamiento.
     */
    @Test
    public void createEntrenamientoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EntrenamientoEntity newEntity = factory.manufacturePojo(EntrenamientoEntity.class);
        EntrenamientoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        EntrenamientoEntity entity = em.find(EntrenamientoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para consultar la lista de entrenamientos.
     */
    @Test
    public void getEntrenamientosTest() {
        List<EntrenamientoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EntrenamientoEntity ent : list) {
            boolean found = false;
            for (EntrenamientoEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Entrenamiento.
     */
    @Test
    public void getEntrenamientoTest() {
        EntrenamientoEntity entity = data.get(0);
        EntrenamientoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar un Entrenamiento.
     */
    @Test
    public void updateEntrenamientoTest() {
        EntrenamientoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EntrenamientoEntity newEntity = factory.manufacturePojo(EntrenamientoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        EntrenamientoEntity resp = em.find(EntrenamientoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un Entrenamiento.
     */
    @Test
    public void deleteEntrenamientoTest() {
        EntrenamientoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        EntrenamientoEntity deleted = em.find(EntrenamientoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
