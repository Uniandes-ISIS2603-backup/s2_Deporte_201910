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
    public CanchaPersistence propietarioPersistence;

    @PersistenceContext
    public EntityManager em;

    @Inject
    UserTransaction utx;

    public List<CanchaEntity> data = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
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
     * Prueba para crear un Propietario.
     */
    @Test
    public void createPropietarioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CanchaEntity newEntity = factory.manufacturePojo(CanchaEntity.class);/*
        CanchaEntity result = propietarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CanchaEntity entity = em.find(CanchaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());*/
    }

    /**
     * Prueba para consultar la lista de Propietarios.
     */
    @Test
    public void getPropietariosTest() {
        List<CanchaEntity> list = propietarioPersistence.findAll();/*
        Assert.assertEquals(data.size(), list.size());
        for (CanchaEntity ent : list) {
            boolean found = false;
            for (CanchaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }*/
    }

    /**
     * Prueba para consultar un Propietario.
     */
    @Test
    public void getPropietarioTest() {/*
        CanchaEntity entity = data.get(0);
        CanchaEntity newEntity = propietarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDireccion(), newEntity.getDireccion());
        Assert.assertEquals(entity.getCiudad(), newEntity.getCiudad());*/
    }

    /**
     * Prueba para actualizar un propietario.
     */
    @Test
    public void updatePropietarioTest() {/*
        CanchaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CanchaEntity newEntity = factory.manufacturePojo(CanchaEntity.class);

        newEntity.setId(entity.getId());

        propietarioPersistence.update(newEntity);

        CanchaEntity resp = em.find(CanchaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getDireccion(), resp.getDireccion());*/
    }

    /**
     * Prueba para eliminar un Propietario.
     */
    @Test
    public void deletePropietarioTest() {/*
        CanchaEntity entity = data.get(0);
        propietarioPersistence.delete(entity.getId());
        CanchaEntity deleted = em.find(CanchaEntity.class, entity.getId());
        Assert.assertNull(deleted);*/
    }
}
