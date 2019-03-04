/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.PartidoLogic;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.PartidoPersistence;
import java.util.ArrayList;
import java.util.Date;
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
import static org.junit.Assert.fail;
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
public class PartidoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PartidoLogic partidoLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PartidoEntity> data = new ArrayList<PartidoEntity>();
    
      /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PartidoEntity.class.getPackage())
                .addPackage(PartidoLogic.class.getPackage())
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
       for (int i = 0; i < 3; i++) {
            PartidoEntity entity = factory.manufacturePojo(PartidoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Partido
     *
     * @throws co.edu.uniandes.csw.poststore.exceptions.BusinessLogicException
     */
    @Test
    public void createPartidoTest() throws BusinessLogicException {
        PartidoEntity newEntity = factory.manufacturePojo(PartidoEntity.class);
        newEntity.setFecha(new Date(2900, 1, 2, 1, 1));
        PartidoEntity result = partidoLogic.createPartido(newEntity);
        Assert.assertNotNull(result);
        PartidoEntity entity = em.find(PartidoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());        
    }
    
    @Test
    public void createPartidoFechaInvalidad()
    {
        PartidoEntity newEntity = factory.manufacturePojo(PartidoEntity.class);
        Date f = new Date();
        long l=Long.parseLong("951777954000");
        f.setTime(l);
        newEntity.setFecha(f);
        try
        {
            PartidoEntity result = partidoLogic.createPartido(newEntity);
            fail();
        }
        catch(Exception e)
        {
            //Pasa el test
        }
    }
    
    @Test
    public void createPartidoEquiposInvalidos()
    {
        ArrayList<EquipoEntity> prueba = new ArrayList<EquipoEntity>();
        for(int i =0; i<5;i++)
        {
            prueba.add(factory.manufacturePojo(EquipoEntity.class));
        }
        PartidoEntity newEntity = factory.manufacturePojo(PartidoEntity.class);
        newEntity.setEquipos(prueba);
        try
        {
            PartidoEntity result = partidoLogic.createPartido(newEntity);
            fail();
        }
        catch(Exception e)
        {
            //Pasa el test
        }        
    }
    
    @Test
    public void createPartidoEquiposRepetidos()
    {
        ArrayList<EquipoEntity> prueba = new ArrayList<EquipoEntity>();
        EquipoEntity p = factory.manufacturePojo(EquipoEntity.class);
        prueba.add(p);
        prueba.add(p);
        PartidoEntity newEntity = factory.manufacturePojo(PartidoEntity.class);
        newEntity.setEquipos(prueba);
        try
        {
            PartidoEntity result = partidoLogic.createPartido(newEntity);
            fail();
        }
        catch(Exception e)
        {
            //Pasa el test
        }
    }
      
      /**
     * Prueba para consultar la lista de partidos.
     */
    @Test
    public void getPartidosTest() {
        List<PartidoEntity> list = partidoLogic.getPartidos();
        Assert.assertEquals(data.size(), list.size());
        for (PartidoEntity entity : list) {
            boolean found = false;
            for (PartidoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un partido.
     */
    @Test
    public void getPartidoTest() {
        PartidoEntity entity = data.get(0);
        PartidoEntity resultEntity = partidoLogic.getPartido(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void getPartidoNull()
    {        
        PartidoEntity resultEntity = partidoLogic.getPartido(Long.MAX_VALUE);
        Assert.assertNull(resultEntity);
    }
    
    /**
     * Prueba para actualizar un Partido.
     *
     * @throws co.edu.uniandes.csw.poststore.exceptions.BusinessLogicException
     */
    @Test
    public void updatePartidoTest() throws BusinessLogicException {
        PartidoEntity entity = data.get(0);
        PartidoEntity pojoEntity = factory.manufacturePojo(PartidoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setFecha(new Date(1900, 1, 2, 1, 1));
        partidoLogic.updatePartido(pojoEntity.getId(), pojoEntity);
        PartidoEntity resp = em.find(PartidoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    
    }  
    
    @Test
    public void updatePartidoFechaInvalida()
    {
        PartidoEntity entity = data.get(0);
        PartidoEntity pojoEntity = factory.manufacturePojo(PartidoEntity.class);
        pojoEntity.setId(entity.getId());
        Date f = new Date();
        long l=Long.parseLong("951777954000");
        f.setTime(l);
        pojoEntity.setFecha(f);
        try
        {
            partidoLogic.updatePartido(pojoEntity.getId(), pojoEntity);
            fail();
        }
        catch(Exception e)
        {
            //Pasa el test
        }        
    }
    
    @Test
    public void updatePartidoEquiposRepetidos()
    {
        PartidoEntity entity = data.get(0);
        PartidoEntity pojoEntity = factory.manufacturePojo(PartidoEntity.class);
        pojoEntity.setId(entity.getId());
        ArrayList<EquipoEntity> eq = new ArrayList<EquipoEntity>();
        EquipoEntity e = new EquipoEntity();
        eq.add(e);
        eq.add(e);
        pojoEntity.setEquipos(eq);
        try
        {
            partidoLogic.updatePartido(pojoEntity.getId(), pojoEntity);
            fail();
        }
         catch(Exception ex)
        {
            //Pasa el test
        }   
    }
    
    @Test
    public void updatePartidoEquiposInvalidos()
    {
        PartidoEntity entity = data.get(0);
        PartidoEntity pojoEntity = factory.manufacturePojo(PartidoEntity.class);
        pojoEntity.setId(entity.getId());
        ArrayList<EquipoEntity> eq = new ArrayList<EquipoEntity>();
        for(int i = 0; i<5; i++)
        {
            eq.add(factory.manufacturePojo(EquipoEntity.class));
        }
        pojoEntity.setEquipos(eq);
         try
        {
            partidoLogic.updatePartido(pojoEntity.getId(), pojoEntity);
            fail();
        }
         catch(Exception ex)
        {
            //Pasa el test
        }   
    }
    
    /**
     * Prueba para eliminar un Partido.
     *
     * @throws co.edu.uniandes.csw.poststore.exceptions.BusinessLogicException
     */
    @Test
    public void deletePartidoTest() throws BusinessLogicException {
        PartidoEntity entity = data.get(0);        
        partidoLogic.deletePartido(entity.getId());
        PartidoEntity deleted = em.find(PartidoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
