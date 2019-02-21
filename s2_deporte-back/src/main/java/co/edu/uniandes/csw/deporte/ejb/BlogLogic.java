/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class BlogLogic {
    private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());

    @Inject
    private BlogPersistence persistence;

  /**
     * Guardar un nuevo libro
     *
     * @param blogEntity La entidad de tipo libro del nuevo libro a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     */
    public BlogEntity createBlog(BlogEntity blogEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del Blog");
        if( blogEntity.getNombre() == null)
        {
            throw new BusinessLogicException("Debe tener un nombre");
        }
       if(persistence.findByName(blogEntity.getNombre()) != null)
       {
           throw new BusinessLogicException("Ya existe un blog con ese nombre");

       }
               persistence.create(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del blog");
        return blogEntity;
    }
    
     /**
     * Devuelve todos los libros que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<BlogEntity> getBlogs() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los blogs");
        List<BlogEntity> blogs = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los blogs");
        return blogs;
    }
    
    /**
     * Busca un libro por ID
     *
     * @param blogsId El id del libro a buscar
     * @return El libro encontrado, null si no lo encuentra.
     */
    public BlogEntity getBlog(Long blogsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el blog con id = {0}", blogsId);
        BlogEntity blogEntity = persistence.find(blogsId);
        if (blogEntity == null) {
            LOGGER.log(Level.SEVERE, "El blog con el id = {0} no existe", blogsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el blog con id = {0}", blogsId);
        return blogEntity;
    }
    
    /**
     * Actualizar un libro por ID
     *
     * @param blogsId El ID del libro a actualizar
     * @param blogEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public BlogEntity updateBlog(Long blogsId, BlogEntity blogEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el blog con id = {0}", blogsId);
        if (!validateNombre(blogEntity.getNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
        BlogEntity newEntity = persistence.update(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el blog con id = {0}", blogEntity.getId());
        return newEntity;
    }
    
     private boolean validateNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
    }
     
     /**
     * Eliminar un libro por ID
     *
     * @param blogsId El ID del libro a eliminar
     * @throws BusinessLogicException si el libro tiene autores asociados
     */
    public void deleteBlog(Long blogsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el blog con id = {0}", blogsId);
        
        persistence.delete(blogsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el blog con id = {0}", blogsId);
    }

}
