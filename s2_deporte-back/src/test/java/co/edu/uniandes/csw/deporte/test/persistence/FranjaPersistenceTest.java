/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import co.edu.uniandes.csw.deporte.persistence.FranjaPersistence;
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
public class FranjaPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaEntity.class.getPackage())
                .addPackage(FranjaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase FranjaPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    public FranjaPersistence persistence;

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
    public List<FranjaEntity> data = new ArrayList<FranjaEntity>();

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
        em.createQuery("DELETE  FROM FranjaEntity").executeUpdate();
    }

    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FranjaEntity entity = factory.manufacturePojo(FranjaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Franja.
     */
    @Test
    public void createtFranjaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FranjaEntity newEntity = factory.manufacturePojo(FranjaEntity.class);
        FranjaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        FranjaEntity entity = em.find(FranjaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de franjas.
     */
    @Test
    public void getFranjasTest() {
        List<FranjaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FranjaEntity ent : list) {
            boolean found = false;
            for (FranjaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Franja.
     */
    @Test
    public void getFranjaTest() {
        FranjaEntity entity = data.get(0);
        FranjaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para actualizar una franja.
     */
    @Test
    public void updateFranjaTest() {
        FranjaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FranjaEntity newEntity = factory.manufacturePojo(FranjaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        FranjaEntity resp = em.find(FranjaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar una franja.
     */
    @Test
    public void deleteFranjaTest() {
        FranjaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        FranjaEntity deleted = em.find(FranjaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
