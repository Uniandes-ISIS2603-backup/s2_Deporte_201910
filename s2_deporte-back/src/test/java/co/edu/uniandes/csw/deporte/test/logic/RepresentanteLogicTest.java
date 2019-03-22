/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.RepresentanteLogic;
import co.edu.uniandes.csw.deporte.entities.RepresentanteEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
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
public class RepresentanteLogicTest 
{
    public PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    public RepresentanteLogic representanteLogic;
    
     @PersistenceContext
    public EntityManager em;

    @Inject
    public UserTransaction utx;
    
    public List<RepresentanteEntity> data = new ArrayList<RepresentanteEntity>();
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RepresentanteEntity.class.getPackage())
                .addPackage(RepresentanteLogic.class.getPackage())
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();        
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
       for (int i = 0; i < 3; i++) {
            RepresentanteEntity entity = factory.manufacturePojo(RepresentanteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createRepresentanteTest() throws BusinessLogicException
    {
        RepresentanteEntity newEntity = factory.manufacturePojo(RepresentanteEntity.class);
        System.out.println(newEntity.getRepresenta()+"---"+newEntity.representante);
        RepresentanteEntity res = representanteLogic.createRepresentante(newEntity);
        Assert.assertNotNull(res);
        RepresentanteEntity nuevo = em.find(RepresentanteEntity.class, res.getId());
        Assert.assertEquals(newEntity.getId(), nuevo.getId());
        Assert.assertEquals(newEntity.getRepresenta().getId(), nuevo.getRepresenta().getId());
        Assert.assertEquals(newEntity.getRepresentante().getId(), nuevo.getRepresentante().getId());
    }    
    @Test
    public void createRepresentateClienteNull()
    {
        
    }    
    @Test
    public void createRepresentanteEquipoNull()
    {
        
    }
    @Test
    public void createRepresentateYaCreado()
    {
        
    }
    @Test
    public void createRepresentateEquipoYaRepresentado()
    {
        
    }
    @Test
    public void createRepresentanteClienteYaRepresentando()
    {
        
    }
    @Test
    public void getRepresentante()
    {
        
    }
    @Test
    public void getRepresentanteNull()
    {
        
    }
    @Test
    public void deleteRepresentante()
    {
        
    }
    @Test
    public void updateRepresentante()
    {
        
    }
    @Test
    public void updateRepresentanteEquipoRepresentado()
    {
        
    }
}
