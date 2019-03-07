/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.CampeonatoLogic;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.CampeonatoPersistence;
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
 * @author Juan Camilo Garcia
 */
@RunWith(Arquillian.class)
public class CampeonatoLogicTest {
    public PodamFactory factory = new PodamFactoryImpl();

    @Inject
    public CampeonatoLogic campeonatoLogic;
    
    @PersistenceContext
    public EntityManager em;

    @Inject
    public UserTransaction utx;

    public List<CampeonatoEntity> data = new ArrayList<CampeonatoEntity>();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CampeonatoEntity.class.getPackage())
                .addPackage(CampeonatoLogic.class.getPackage())
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
        em.createQuery("delete from BlogEntity").executeUpdate();
        em.createQuery("delete from CampeonatoEntity").executeUpdate();
        
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
       for (int i = 0; i < 3; i++) {
            CampeonatoEntity entity = factory.manufacturePojo(CampeonatoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Campeonato
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test
    public void createCampeonatoTest() throws BusinessLogicException {
        CampeonatoEntity newEntity = factory.manufacturePojo(CampeonatoEntity.class);
        CampeonatoEntity result = campeonatoLogic.createCampeonato(newEntity);
        Assert.assertNotNull(result);
        CampeonatoEntity entity = em.find(CampeonatoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }
    
     /**
     * Prueba para crear un Campeonato con nombre repetido
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCampeonatoTestConNombreRepetido() throws BusinessLogicException {
        CampeonatoEntity newEntity = factory.manufacturePojo(CampeonatoEntity.class);
        newEntity.setNombre("aa");
        campeonatoLogic.createCampeonato(newEntity);
        CampeonatoEntity repeatedEntity = factory.manufacturePojo(CampeonatoEntity.class);
        repeatedEntity.setNombre("aa");
        campeonatoLogic.createCampeonato(repeatedEntity);
        
    }
    
     /**
     * Prueba para crear un Campeonato con nombre inválido
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCampeonatoTestConNombreinvalido() throws BusinessLogicException {
        CampeonatoEntity newEntity = factory.manufacturePojo(CampeonatoEntity.class);
        newEntity.setNombre(null);
        campeonatoLogic.createCampeonato(newEntity);
       
    }
    
   

    
      /**
     * Prueba para consultar la lista de Campeonatos.
     */
    @Test
    public void getCampeonatosTest() {
        List<CampeonatoEntity> list = campeonatoLogic.getCampeonatos();
        Assert.assertEquals(data.size(), list.size());
        for (CampeonatoEntity entity : list) {
            boolean found = false;
            for (CampeonatoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        CampeonatoEntity resultEntity = campeonatoLogic.getCampeonato(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());  
    }
    
    /**
     * Prueba para actualizar un Campeonato.
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test
    public void updateCampeonatoTest() throws BusinessLogicException {
        CampeonatoEntity entity = data.get(0);
        CampeonatoEntity pojoEntity = factory.manufacturePojo(CampeonatoEntity.class);
        pojoEntity.setId(entity.getId());
        campeonatoLogic.updateCampeonato(pojoEntity.getId(), pojoEntity);
        CampeonatoEntity resp = em.find(CampeonatoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }
     /**
     * Prueba para actualizar un Campeonato con Cnombre inválido.
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCampeonatoConNombreInvalidoTest() throws BusinessLogicException {
        CampeonatoEntity entity = data.get(0);
        CampeonatoEntity pojoEntity = factory.manufacturePojo(CampeonatoEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entity.getId());
        campeonatoLogic.updateCampeonato(pojoEntity.getId(), pojoEntity);
    }
     /**
     * Prueba para actualizar un Campeonato con Nombre inválido.
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCampeonatoConNombreInvalidoTest2() throws BusinessLogicException {
        CampeonatoEntity entity = data.get(0);
        CampeonatoEntity pojoEntity = factory.manufacturePojo(CampeonatoEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(entity.getId());
        campeonatoLogic.updateCampeonato(pojoEntity.getId(), pojoEntity);
    }

   
    
    /**
     * Prueba para eliminar un Campeonato.
     *
     * @throws co.edu.uniandes.csw.campeonatostore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteCampeonatoTest() throws BusinessLogicException {
        CampeonatoEntity entity = data.get(0);
        campeonatoLogic.deleteCampeonato(entity.getId());
        CampeonatoEntity deleted = em.find(CampeonatoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


}
