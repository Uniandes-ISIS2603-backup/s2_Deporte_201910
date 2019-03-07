/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

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
 * @author Juan Camilo Garcia
 */
@Stateless
public class PostLogic {
     private static final Logger LOGGER = Logger.getLogger(PostLogic.class.getName());

    @Inject
    private PostPersistence persistence;
    
     /**
     * Guardar un nuevo post
     *
     * @param postEntity La entidad de tipo post del nuevo post a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre es inválido o ya existe en la
     * persistencia.
     */
    public PostEntity createPost(PostEntity postEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del Post");
        
               persistence.create(postEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del post");
        return postEntity;
    }
    
     /**
     * Devuelve todos los posts que hay en la base de datos.
     *
     * @return Lista de entidades de tipo post.
     */
    public List<PostEntity> getPosts() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los posts");
        List<PostEntity> posts = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los posts");
        return posts;
    }
    
    /**
     * Busca un post por ID
     *
     * @param postsId El id del post a buscar
     * @return El post encontrado, null si no lo encuentra.
     */
    public PostEntity getPost(Long postsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el post con id = {0}", postsId);
        PostEntity postEntity = persistence.find(postsId);
        if (postEntity == null) {
            LOGGER.log(Level.SEVERE, "El post con el id = {0} no existe", postsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el post con id = {0}", postsId);
        return postEntity;
    }
    
    /**
     * Actualizar un post por ID
     *
     * @param postsId El ID del post a actualizar
     * @param postEntity La entidad del post con los cambios deseados
     * @return La entidad del post luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public PostEntity updatePost (Long postsId, PostEntity postEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el post con id = {0}", postsId);
        
        PostEntity newEntity = persistence.update(postEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el post con id = {0}", postEntity.getId());
        return newEntity;
    }
    
    
     
     /**
     * Eliminar un post por ID
     *
     * @param postsId El ID del post a eliminar
     * @throws BusinessLogicException si el post tiene autores asociados
     */
    public void deletePost(Long postsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el post con id = {0}", postsId);
        
        persistence.delete(postsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el post con id = {0}", postsId);
    }


}
