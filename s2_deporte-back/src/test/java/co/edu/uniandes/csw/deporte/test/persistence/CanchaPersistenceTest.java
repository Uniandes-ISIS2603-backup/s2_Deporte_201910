/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.persistence.CanchaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
public class CanchaPersistenceTest {

    @Inject
    public CanchaPersistence canchaPersistence;

    @PersistenceContext
    public EntityManager em;

    @Inject
    UserTransaction utx;

    public List<CanchaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CanchaEntity.class.getPackage())
                .addPackage(CanchaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    public void clearData() {
        em.createQuery("delete from CanchaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CanchaEntity entity = factory.manufacturePojo(CanchaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Cancha.
     */
    @Test
    public void createCanchaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CanchaEntity newEntity;
        newEntity = factory.manufacturePojo(CanchaEntity.class);
        CanchaEntity result = null;
        //canchaPersistence.create(newEntity);

        //    Assert.assertNotNull(result);
        //    CanchaEntity entity = em.find(CanchaEntity.class, result.getId());
        //    Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
    }

    /**
     * Prueba para eliminar una Cancha.
     *
     *
     */
    /*  @Test
    public void deleteCanchaTest() {
        CanchaEntity entity = data.get(0);
        cp.delete(entity.getId());
        CanchaEntity deleted = em.find(CanchaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }*/
}
