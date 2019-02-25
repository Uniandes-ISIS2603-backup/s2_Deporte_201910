/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.persistence.EquipoPersistence;
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
public class EquipoPersistenceTest 
{
    @Inject
    private EquipoPersistence equipoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<EquipoEntity> data = new ArrayList<>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Equipo.
     */
    @Test
    public void createEquipoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity result = equipoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EquipoEntity entity = em.find(EquipoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Equipos.
     */
    @Test
    public void getEquiposTest() {
        List<EquipoEntity> list = equipoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity ent : list) {
            boolean found = false;
            for (EquipoEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un equipo.
     */
    @Test
    public void getEquipoTest() {
        EquipoEntity entity = data.get(0);
        EquipoEntity newEntity = equipoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());        
    }

    /**
     * Prueba para actualizar un Equipo.
     */
    @Test
    public void updateEquipoTest() {
        EquipoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        newEntity.setId(entity.getId());
        equipoPersistence.update(newEntity);
        EquipoEntity resp = em.find(EquipoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un cliente.
     */
    @Test
    public void deleteEquipoTest() {
        EquipoEntity entity = data.get(0);
        equipoPersistence.delete(entity.getId());
        EquipoEntity deleted = em.find(EquipoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}