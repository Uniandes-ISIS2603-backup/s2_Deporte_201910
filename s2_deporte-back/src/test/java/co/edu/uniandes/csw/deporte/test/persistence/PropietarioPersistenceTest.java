/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import co.edu.uniandes.csw.deporte.persistence.PropietarioPersistence;
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
public class PropietarioPersistenceTest {

    @Inject
    public PropietarioPersistence propietarioPersistence;

    @PersistenceContext
    public EntityManager em;

    @Inject
    UserTransaction utx;

    public List<PropietarioEntity> data = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PropietarioEntity.class.getPackage())
                .addPackage(PropietarioPersistence.class.getPackage())
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
        em.createQuery("delete from PropietarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PropietarioEntity entity = factory.manufacturePojo(PropietarioEntity.class);

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
        PropietarioEntity newEntity = factory.manufacturePojo(PropietarioEntity.class);
        PropietarioEntity result = propietarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PropietarioEntity entity = em.find(PropietarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Propietarios.
     */
    @Test
    public void getPropietariosTest() {
        List<PropietarioEntity> list = propietarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PropietarioEntity ent : list) {
            boolean found = false;
            for (PropietarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Propietario.
     */
    @Test
    public void getPropietarioTest() {
        PropietarioEntity entity = data.get(0);
        PropietarioEntity newEntity = propietarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getNumCanchas(), newEntity.getNumCanchas());
    }

    /**
     * Prueba para actualizar un propietario.
     */
    @Test
    public void updateAuthorTest() {
        PropietarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PropietarioEntity newEntity = factory.manufacturePojo(PropietarioEntity.class);

        newEntity.setId(entity.getId());

        propietarioPersistence.update(newEntity);

        PropietarioEntity resp = em.find(PropietarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un Author.
     */
    @Test
    public void deleteAuthorTest() {
        PropietarioEntity entity = data.get(0);
        propietarioPersistence.delete(entity.getId());
        PropietarioEntity deleted = em.find(PropietarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
