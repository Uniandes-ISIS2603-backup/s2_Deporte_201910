/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.PostLogic;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.PostPersistence;
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
public class PostLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PostLogic postLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PostEntity> data = new ArrayList<PostEntity>();
    
      /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PostEntity.class.getPackage())
                .addPackage(PostLogic.class.getPackage())
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
       for (int i = 0; i < 3; i++) {
            PostEntity entity = factory.manufacturePojo(PostEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Post
     *
     * @throws co.edu.uniandes.csw.poststore.exceptions.BusinessLogicException
     */
    @Test
    public void createPostTest() throws BusinessLogicException {
        PostEntity newEntity = factory.manufacturePojo(PostEntity.class);
        PostEntity result = postLogic.createPost(newEntity);
        Assert.assertNotNull(result);
        PostEntity entity = em.find(PostEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
    }
      
      /**
     * Prueba para consultar la lista de Posts.
     */
    @Test
    public void getPostsTest() {
        List<PostEntity> list = postLogic.getPosts();
        Assert.assertEquals(data.size(), list.size());
        for (PostEntity entity : list) {
            boolean found = false;
            for (PostEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        PostEntity resultEntity = postLogic.getPost(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
         
    }
    
    /**
     * Prueba para actualizar un Post.
     *
     * @throws co.edu.uniandes.csw.poststore.exceptions.BusinessLogicException
     */
    @Test
    public void updatePostTest() throws BusinessLogicException {
        PostEntity entity = data.get(0);
        PostEntity pojoEntity = factory.manufacturePojo(PostEntity.class);
        pojoEntity.setId(entity.getId());
        postLogic.updatePost(pojoEntity.getId(), pojoEntity);
        PostEntity resp = em.find(PostEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        
    }
    
   

   
    
    /**
     * Prueba para eliminar un Post.
     *
     * @throws co.edu.uniandes.csw.poststore.exceptions.BusinessLogicException
     */
    @Test
    public void deletePostTest() throws BusinessLogicException {
        PostEntity entity = data.get(0);
        
        postLogic.deletePost(entity.getId());
        PostEntity deleted = em.find(PostEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


}
