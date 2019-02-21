/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.persistence.PostPersistence;
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
public class PostPersistenceTest {
    @Inject
    PostPersistence postPersistence;
    @PersistenceContext
    private EntityManager em;
    
        private List<PostEntity> data = new ArrayList<>();

    @Inject
    UserTransaction utx;

    @Deployment
    public static JavaArchive createDeployment()
    {
       return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PostEntity.class.getPackage())
                .addPackage(PostPersistence.class.getPackage())
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
        em.createQuery("delete from PostEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PostEntity entity = factory.manufacturePojo(PostEntity.class);

            em.persist(entity);
            data.add(entity);
            
        }
    }
    /**
     * Prueba para crear un Post.
     */
    @Test
    public void createPostTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PostEntity newEntity;
        newEntity = factory.manufacturePojo(PostEntity.class);
        PostEntity result = postPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        PostEntity entity = em.find(PostEntity.class, result.getId());
        
        
        
            }
  /**
     * Prueba para consultar la lista de Posts.
     */
   
    @Test
    public void getPostsTest()
    {
        List<PostEntity> list = postPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(PostEntity enti : list)
        {
            boolean found = false;
            for(PostEntity entity : data)
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
     * Prueba para consultar un Post.
     */
    @Test
    public void getPostTest() {
        PostEntity entity = data.get(0);
        
        PostEntity newEntity = postPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
    }
    
    /**
     * Prueba para eliminar un Post.
     */
    @Test
    public void deletePostTest() {
        PostEntity entity = data.get(0);
        postPersistence.delete(entity.getId());
        PostEntity deleted = em.find(PostEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Post.
     */
    @Test
    public void updatePostTest() {
        PostEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PostEntity newEntity = factory.manufacturePojo(PostEntity.class);

        newEntity.setId(entity.getId());

        postPersistence.update(newEntity);

        PostEntity resp = em.find(PostEntity.class, entity.getId());

    
    }
    
   
  
}
