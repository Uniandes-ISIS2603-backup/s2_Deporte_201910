/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.persistence;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
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

public class BlogPersistenceTest {
    @Inject
     public BlogPersistence blogPersistance;
    
    @PersistenceContext
    public EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    
    public List<BlogEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
       return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogPersistence.class.getPackage())
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
        em.createQuery("delete from BlogEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BlogEntity entity = factory.manufacturePojo(BlogEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Blog.
     */
    @Test
    public void createBlogTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        BlogEntity newEntity;
        newEntity = factory.manufacturePojo(BlogEntity.class);
        BlogEntity result = blogPersistance.create(newEntity);
        
        Assert.assertNotNull(result);
        
        BlogEntity entity = em.find(BlogEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
            }
    /**
     * Prueba para consultar la lista de Blogs.
     */
   
    @Test
    public void getBlogsTest()
    {
        List<BlogEntity> list = blogPersistance.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(BlogEntity enti : list)
        {
            boolean found = false;
            for(BlogEntity entity : data)
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
     * Prueba para consultar un Blog.
     */
    @Test
    public void getBlogTest() {
        BlogEntity entity = data.get(0);
        
        BlogEntity newEntity = blogPersistance.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        
    }
    
    /**
     * Prueba para eliminar un Blog.
     */
    @Test
    public void deleteBlogTest() {
        BlogEntity entity = data.get(0);
        blogPersistance.delete(entity.getId());
        BlogEntity deleted = em.find(BlogEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Blog.
     */
    @Test
    public void updateBlogTest() {
        BlogEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);

        newEntity.setId(entity.getId());

        blogPersistance.update(newEntity);

        BlogEntity resp = em.find(BlogEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    
    }
    
     /**
     * Prueba para consultasr un Blog por nombre.
     */
    @Test
    public void findBlogByNombreTest() {
        BlogEntity entity = data.get(0);
        BlogEntity newEntity = blogPersistance.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = blogPersistance.findByName(null);
        Assert.assertNull(newEntity);
    }
}
