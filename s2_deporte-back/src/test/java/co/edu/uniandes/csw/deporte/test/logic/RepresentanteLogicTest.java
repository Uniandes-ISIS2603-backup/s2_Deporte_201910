/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.RepresentanteLogic;
import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
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
    
    public List<RepresentanteEntity> dataR = new ArrayList<RepresentanteEntity>();
    public List<EquipoEntity> dataE = new ArrayList<EquipoEntity>();
    public List<ClienteEntity> dataC = new ArrayList<ClienteEntity>();
    
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
            RepresentanteEntity entityR = factory.manufacturePojo(RepresentanteEntity.class);
            ClienteEntity entityC = factory.manufacturePojo(ClienteEntity.class);
            EquipoEntity entityE = factory.manufacturePojo(EquipoEntity.class);
            
            entityR.setRepresenta(entityE);
            entityR.setRepresentante(entityC);
            
            em.persist(entityR);
            em.persist(entityC);
            em.persist(entityE);
            
            dataR.add(entityR);
            dataC.add(entityC);
            dataE.add(entityE);
        }
    }
    
    @Test
    public void createRepresentanteTest() throws BusinessLogicException
    {
        RepresentanteEntity newEntity = factory.manufacturePojo(RepresentanteEntity.class);
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setRepresenta(equipo);
        newEntity.setRepresentante(cliente);
        System.out.println(newEntity.getRepresenta()+"---"+newEntity.representante);
        RepresentanteEntity res = representanteLogic.createRepresentante(newEntity);
        Assert.assertNotNull(res);
        RepresentanteEntity nuevo = em.find(RepresentanteEntity.class, res.getId());
        System.out.println(nuevo);
        Assert.assertEquals(newEntity.getId(), nuevo.getId());
        Assert.assertEquals(newEntity.getRepresenta().getId(), nuevo.getRepresenta().getId());
        Assert.assertEquals(newEntity.getRepresentante().getId(), nuevo.getRepresentante().getId());
    }    
    @Test
    public void createRepresentateClienteNull()
    {
        RepresentanteEntity newEntity = factory.manufacturePojo(RepresentanteEntity.class);
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        newEntity.setRepresenta(equipo);
        
        try
        {
            RepresentanteEntity res = representanteLogic.createRepresentante(newEntity);
            Assert.fail();
        }
        catch(Exception e)
        {
            
        }
        
    }    
    @Test
    public void createRepresentanteEquipoNull()
    {
        RepresentanteEntity newEntity = factory.manufacturePojo(RepresentanteEntity.class);
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
        
        newEntity.setRepresentante(cliente);
        try
        {
            RepresentanteEntity res = representanteLogic.createRepresentante(newEntity);
            Assert.fail();
        }
        catch(Exception e)
        {
            
        }
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
