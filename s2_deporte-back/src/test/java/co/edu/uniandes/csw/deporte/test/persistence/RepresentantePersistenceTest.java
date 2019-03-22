/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.RepresentanteEntity;
import co.edu.uniandes.csw.deporte.persistence.RepresentantePersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class RepresentantePersistenceTest 
{
     @Inject
    public RepresentantePersistence representantePersistence;

    @PersistenceContext
    public EntityManager em;

    @Inject
    UserTransaction utx;

    public List<RepresentanteEntity> data = new ArrayList<>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RepresentanteEntity.class.getPackage())
                .addPackage(RepresentantePersistence.class.getPackage())
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
     */
    public void clearData() {
        em.createQuery("delete from RepresentanteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RepresentanteEntity entity = factory.manufacturePojo(RepresentanteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Author.
     */
    @Test
    public void createRepresentanteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RepresentanteEntity newEntity = factory.manufacturePojo(RepresentanteEntity.class);
        RepresentanteEntity result = representantePersistence.create(newEntity);

        Assert.assertNotNull(result);

        RepresentanteEntity entity = em.find(RepresentanteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de representante.
     */
    @Test
    public void getRepresentantesTest() {
        List<RepresentanteEntity> list = representantePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (RepresentanteEntity ent : list) {
            boolean found = false;
            for (RepresentanteEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un representante.
     */
    @Test
    public void getRepresentanteTest() {
        RepresentanteEntity entity = data.get(0);
        RepresentanteEntity newEntity = representantePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar un representante.
     */
    @Test
    public void updateRepresentanteTest() {
        RepresentanteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RepresentanteEntity newEntity = factory.manufacturePojo(RepresentanteEntity.class);

        newEntity.setId(entity.getId());

        representantePersistence.update(newEntity);

        RepresentanteEntity resp = em.find(RepresentanteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un representante.
     */
    @Test
    public void deleteRepresentanteTest() {
        RepresentanteEntity entity = data.get(0);
        representantePersistence.delete(entity.getId());
        RepresentanteEntity deleted = em.find(RepresentanteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
