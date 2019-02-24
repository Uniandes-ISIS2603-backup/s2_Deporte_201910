/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
import co.edu.uniandes.csw.deporte.persistence.PartidoPersistence;
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
 * @partido estudiante
 */
@RunWith(Arquillian.class)
public class PartidoPersistenceTest
{
@Inject
    private PartidoPersistence PartidoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PartidoEntity> data = new ArrayList<>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PartidoEntity.class.getPackage())
                .addPackage(PartidoPersistence.class.getPackage())
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
    private void clearData() {
        em.createQuery("delete from PartidoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PartidoEntity entity = factory.manufacturePojo(PartidoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Partido.
     */
    @Test
    public void createPartidoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PartidoEntity newEntity = factory.manufacturePojo(PartidoEntity.class);
        PartidoEntity result = PartidoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PartidoEntity entity = em.find(PartidoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Partidos.
     */
    @Test
    public void getPartidosTest() {
        List<PartidoEntity> list = PartidoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PartidoEntity ent : list) {
            boolean found = false;
            for (PartidoEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Partido.
     */
    @Test
    public void getPartidoTest() {
        PartidoEntity entity = data.get(0);
        PartidoEntity newEntity = PartidoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar un Partido.
     */
    @Test
    public void updatePartidoTest() {
        PartidoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PartidoEntity newEntity = factory.manufacturePojo(PartidoEntity.class);
        newEntity.setId(entity.getId());
        PartidoPersistence.update(newEntity);
        PartidoEntity resp = em.find(PartidoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un cliente.
     */
    @Test
    public void deletePartidoTest() {
        PartidoEntity entity = data.get(0);
        PartidoPersistence.delete(entity.getId());
        PartidoEntity deleted = em.find(PartidoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
