/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Camilo Garcia
 */
@Stateless
public class BlogPersistence {
     private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
   /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param blogEntity objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BlogEntity create(BlogEntity blogEntity) {
        LOGGER.log(Level.INFO, "Creando un blog nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(blogEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un blog nuevo");
        return blogEntity;
    }
    
    public BlogEntity find(Long blogId)
    {
        LOGGER.log(Level.INFO, "Consultando blog con id={0}", blogId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(BlogEntity.class, blogId);
    }
    
    public List<BlogEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los blogs");
        Query query = em.createQuery("select u from BlogEntity u");
        return query.getResultList();
    }
    
    public BlogEntity update(BlogEntity blogEntity) {
        LOGGER.log(Level.INFO, "Actualizando el blog con id={0}", blogEntity.getId());
        return em.merge(blogEntity);
    }
    
    public void delete(Long blogId) {
        LOGGER.log(Level.INFO, "Borrando el blog con id={0}", blogId);
        BlogEntity blogEntity = em.find(BlogEntity.class, blogId);
        em.remove(blogEntity);
    }
    
    public BlogEntity findByName(String pNombre)
    {
        LOGGER.log(Level.INFO, "Consultando blogs por nombre ", pNombre);
        TypedQuery query = em.createQuery("Select e From BlogEntity e where e.nombre = :nombre", BlogEntity.class);
        
        query = query.setParameter("nombre", pNombre);
        
        List<BlogEntity> sameNombre = query.getResultList();
        BlogEntity result;
        if(sameNombre == null)
        {
            result = null;
        }
        else if(sameNombre.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar blogs por nombre ", pNombre);
        return result;
    }
    
        
}
