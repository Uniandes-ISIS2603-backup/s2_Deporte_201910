/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.CanchaLogic;
import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.CanchaPersistence;
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
 * @author Santiago Serrano
 */
@RunWith(Arquillian.class)
public class CanchaLogicTest {
    
    public PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    public CanchaLogic canchaLogic;
    
     /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    public EntityManager em;
    
        /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    public UserTransaction utx;
    
        /**
     * Lista que tiene los datos de prueba.
     */
    public List<CanchaEntity> data = new ArrayList<CanchaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CanchaEntity.class.getPackage())
                .addPackage(CanchaLogic.class.getPackage())
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
        for (int i = 0; i < 3; i++) {
            CanchaEntity entity = factory.manufacturePojo(CanchaEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }
    
    /**
     * Prueba para crear un Editorial.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createEditorialTest() throws BusinessLogicException {
        CanchaEntity newEntity = factory.manufacturePojo(CanchaEntity.class);
        CanchaEntity result = canchaLogic.createCancha(newEntity);
        Assert.assertNotNull(result);
        CanchaEntity entity = em.find(CanchaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getCaracterizticas(), entity.getCaracterizticas());
        Assert.assertEquals(newEntity.getContacto(), entity.getContacto());
        Assert.assertEquals(newEntity.getPropietario(), entity.getPropietario());
        Assert.assertEquals(newEntity.getZona(), entity.getZona());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
        /**
     * Prueba para crear un Editorial con el mismo nombre de un Editorial que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCanchaConMismaDireccionTest() throws BusinessLogicException {
        CanchaEntity newEntity = factory.manufacturePojo(CanchaEntity.class);
        newEntity.setDireccion(data.get(0).getDireccion());
        canchaLogic.createCancha(newEntity);
    }
    
        /**
     * Prueba para eliminar un Editorial.
     *
     * @throws co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException
     */
    @Test
    public void deleteCanchaTest() throws BusinessLogicException {
        CanchaEntity entity = data.get(1);
        canchaLogic.deleteCancha(entity.getId());
        CanchaEntity deleted = em.find(CanchaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
