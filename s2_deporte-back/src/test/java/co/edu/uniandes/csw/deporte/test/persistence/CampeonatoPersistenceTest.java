/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.persistence.CampeonatoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class CampeonatoPersistenceTest {
    @Inject
    CampeonatoPersistence campeonatoPersistence;
    @PersistenceContext
    public EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    public List<CampeonatoEntity> data = new ArrayList<>();
    @Deployment
    public static JavaArchive createDeployment()
    {
       return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CampeonatoEntity.class.getPackage())
                .addPackage(CampeonatoPersistence.class.getPackage())
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
        em.createQuery("delete from CampeonatoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CampeonatoEntity entity = factory.manufacturePojo(CampeonatoEntity.class);

            em.persist(entity);
            data.add(entity);
            
        }
    }
    /**
     * Prueba para crear un Campeonato.
     */
    @Test
    public void createCampeonatoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CampeonatoEntity newEntity;
        newEntity = factory.manufacturePojo(CampeonatoEntity.class);
        CampeonatoEntity result = campeonatoPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        CampeonatoEntity entity = em.find(CampeonatoEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
            }
  /**
     * Prueba para consultar la lista de Campeonatos.
     */
   
    @Test
    public void getCampeonatosTest()
    {
        List<CampeonatoEntity> list = campeonatoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(CampeonatoEntity enti : list)
        {
            boolean found = false;
            for(CampeonatoEntity entity : data)
            {
                if(enti.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        
    }
    
    /**
     * Prueba para consultar un Campeonato.
     */
    @Test
    public void getCampeonatoTest() {
        CampeonatoEntity entity = data.get(0);
        
        CampeonatoEntity newEntity = campeonatoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        
    }
    
    /**
     * Prueba para eliminar un Campeonato.
     */
    @Test
    public void deleteCampeonatoTest() {
        CampeonatoEntity entity = data.get(0);
        campeonatoPersistence.delete(entity.getId());
        CampeonatoEntity deleted = em.find(CampeonatoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Campeonato.
     */
    @Test
    public void updateCampeonatoTest() {
        CampeonatoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CampeonatoEntity newEntity = factory.manufacturePojo(CampeonatoEntity.class);

        newEntity.setId(entity.getId());

        campeonatoPersistence.update(newEntity);

        CampeonatoEntity resp = em.find(CampeonatoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    
    }
    
     /**
     * Prueba para consultasr un Campeonato por nombre.
     */
    @Test
    public void findCampeonatoByNombreTest() {
        CampeonatoEntity entity = data.get(0);
        CampeonatoEntity newEntity = campeonatoPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = campeonatoPersistence.findByName(null);
        Assert.assertNull(newEntity);
    }
}
