/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.EquipoLogic;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
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
public class EquipoLogicTest
{
    public PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    public EquipoLogic equipoLogic;
    
     @PersistenceContext
    public EntityManager em;

    @Inject
    public UserTransaction utx;
    
    public List<EquipoEntity> data = new ArrayList<EquipoEntity>();
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoLogic.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from EquipoEntity").executeUpdate();
        em.createQuery("delete from PartidoEntity").executeUpdate();
        
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
       for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createEquipoTest() throws BusinessLogicException 
    {
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity result = equipoLogic.createEquipo(newEntity);
        Assert.assertNotNull(result);
        EquipoEntity entity = em.find(EquipoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());        
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEquipoTestConNombreNull() throws BusinessLogicException {
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        newEntity.setNombre(null);
        equipoLogic.createEquipo(newEntity);        
    }
    
    @Test
    public void getEquiposTest() {
        List<EquipoEntity> list = equipoLogic.getEquipos();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity entity : list) {
            boolean found = false;
            for (EquipoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getEquipoTest() {
        EquipoEntity entity = data.get(0);
        EquipoEntity resultEntity = equipoLogic.getEquipo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());        
    }
    
    
    
}
