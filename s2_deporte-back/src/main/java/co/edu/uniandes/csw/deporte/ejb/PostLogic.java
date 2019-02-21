/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.PostPersistence;
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
public class PostLogic {
     private static final Logger LOGGER = Logger.getLogger(PostLogic.class.getName());

    @Inject
    private PostPersistence persistence;
    
     /**
     * Guardar un nuevo libro
     *
     * @param campeonatoEntity La entidad de tipo libro del nuevo libro a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     */
    public PostEntity createPost(PostEntity postEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del Post");
        
               persistence.create(postEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del post");
        return postEntity;
    }
    
     /**
     * Devuelve todos los libros que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<PostEntity> getPosts() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los posts");
        List<PostEntity> posts = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los posts");
        return posts;
    }
    
    /**
     * Busca un libro por ID
     *
     * @param postsId El id del libro a buscar
     * @return El libro encontrado, null si no lo encuentra.
     */
    public PostEntity getPost(Long postsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el post con id = {0}", postsId);
        PostEntity postEntity = persistence.find(postsId);
        if (postEntity == null) {
            LOGGER.log(Level.SEVERE, "El post con el id = {0} no existe", postsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el campeonato con post = {0}", postsId);
        return postEntity;
    }
    
    /**
     * Actualizar un libro por ID
     *
     * @param postsId El ID del libro a actualizar
     * @param postEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public PostEntity updatePost (Long postsId, PostEntity postEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el post con id = {0}", postsId);
        
        PostEntity newEntity = persistence.update(postEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el post con id = {0}", postEntity.getId());
        return newEntity;
    }
    
    
     
     /**
     * Eliminar un libro por ID
     *
     * @param postsId El ID del libro a eliminar
     * @throws BusinessLogicException si el libro tiene autores asociados
     */
    public void deleteCampeonato(Long postsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el post con id = {0}", postsId);
        
        persistence.delete(postsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el post con id = {0}", postsId);
    }


}
