/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.test.logic;

import co.edu.uniandes.csw.deporte.ejb.BlogLogic;
import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
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
public class BlogLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private BlogLogic blogLogic;
    
     @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<BlogEntity> data = new ArrayList<BlogEntity>();
    
    private List<CampeonatoEntity> blogData = new ArrayList<CampeonatoEntity>();

     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogLogic.class.getPackage())
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
        em.createQuery("delete from BlogEntity").executeUpdate();
        em.createQuery("delete from CampeonatoEntity").executeUpdate();
        
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
       for (int i = 0; i < 3; i++) {
            BlogEntity entity = factory.manufacturePojo(BlogEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Blog
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test
    public void createBlogTest() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        BlogEntity result = blogLogic.createBlog(newEntity);
        Assert.assertNotNull(result);
        BlogEntity entity = em.find(BlogEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }
    
     /**
     * Prueba para crear un Blog con nombre repetido
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createblogTestConNombreRepetido() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setNombre("aa");
        blogLogic.createBlog(newEntity);
        BlogEntity repeatedEntity = factory.manufacturePojo(BlogEntity.class);
        repeatedEntity.setNombre("aa");
        blogLogic.createBlog(repeatedEntity);
        
    }
    
     /**
     * Prueba para crear un Blog con nombre inválido
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBlogTestConNombreinvalido() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setNombre(null);
        blogLogic.createBlog(newEntity);
       
    }
    
      /**
     * Prueba para crear un Blog con descripcion inválida
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBlogTestConDescripcioninvalida() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setDescripcion(null);
        blogLogic.createBlog(newEntity);
       
    }

    
      /**
     * Prueba para consultar la lista de Blogs.
     */
    @Test
    public void getBlogsTest() {
        List<BlogEntity> list = blogLogic.getBlogs();
        Assert.assertEquals(data.size(), list.size());
        for (BlogEntity entity : list) {
            boolean found = false;
            for (BlogEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        BlogEntity resultEntity = blogLogic.getBlog(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());  
    }
    
    /**
     * Prueba para actualizar un Blog.
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateBlogTest() throws BusinessLogicException {
        BlogEntity entity = data.get(0);
        BlogEntity pojoEntity = factory.manufacturePojo(BlogEntity.class);
        pojoEntity.setId(entity.getId());
        blogLogic.updateBlog(pojoEntity.getId(), pojoEntity);
        BlogEntity resp = em.find(BlogEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }
     /**
     * Prueba para actualizar un blog con nombre inválido.
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateBlogConNombreInvalidoTest() throws BusinessLogicException {
        BlogEntity entity = data.get(0);
        BlogEntity pojoEntity = factory.manufacturePojo(BlogEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entity.getId());
        blogLogic.updateBlog(pojoEntity.getId(), pojoEntity);
    }
     /**
     * Prueba para actualizar un blog con nombre inválido.
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateBlogConNombreInvalidoTest2() throws BusinessLogicException {
        BlogEntity entity = data.get(0);
        BlogEntity pojoEntity = factory.manufacturePojo(BlogEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(entity.getId());
        blogLogic.updateBlog(pojoEntity.getId(), pojoEntity);
    }

     /**
     * Prueba para actualizar un blog con Nombre inválido.
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateblogConDescripcionInvalidaTest() throws BusinessLogicException {
        BlogEntity entity = data.get(0);
        BlogEntity pojoEntity = factory.manufacturePojo(BlogEntity.class);
        pojoEntity.setDescripcion(null);
        pojoEntity.setId(entity.getId());
        blogLogic.updateBlog(pojoEntity.getId(), pojoEntity);
    }
    /**
     * Prueba para actualizar un blog con Nombre inválido.
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateblogConDescripcionInvalidaTest2() throws BusinessLogicException {
        BlogEntity entity = data.get(0);
        BlogEntity pojoEntity = factory.manufacturePojo(BlogEntity.class);
        pojoEntity.setDescripcion("");
        pojoEntity.setId(entity.getId());
        blogLogic.updateBlog(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar un blog.
     *
     * @throws co.edu.uniandes.csw.blogstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteBlogTest() throws BusinessLogicException {
        BlogEntity entity = data.get(0);
        blogLogic.deleteBlog(entity.getId());
        BlogEntity deleted = em.find(BlogEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


    }
