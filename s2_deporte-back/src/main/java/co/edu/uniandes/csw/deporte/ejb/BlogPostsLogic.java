/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
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
public class BlogPostsLogic {
    
    private static final Logger LOGGER = Logger.getLogger(BlogPostsLogic.class.getName());

    @Inject
    private PostPersistence bookPersistence;

    @Inject
    private BlogPersistence editorialPersistence;
    
     /**
     * Agregar un book a la editorial
     *
     * @param postId El id libro a guardar
     * @param blogId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public PostEntity addPost(Long postId, Long blogId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la editorial con id = {0}", blogId);
        BlogEntity editorialEntity = editorialPersistence.find(blogId);
        PostEntity bookEntity = bookPersistence.find(postId);
        bookEntity.setBlog(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", blogId);
        return bookEntity;
    }
    
    /**
     * Retorna todos los books asociados a una editorial
     *
     * @param blogId El ID de la editorial buscada
     * @return La lista de libros de la editorial
     */
    public List<PostEntity> getPosts(Long blogId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la editorial con id = {0}", blogId);
        return editorialPersistence.find(blogId).getPosts();
    }
    
     /**
     * Retorna un book asociado a una editorial
     *
     * @param blogId El id de la editorial a buscar.
     * @param postId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public PostEntity getPost(Long blogId, Long postId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la editorial con id = {0} " + blogId, postId);
        List<PostEntity> books = editorialPersistence.find(blogId).getPosts();
        PostEntity bookEntity = bookPersistence.find(postId);
        int index = books.indexOf(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la editorial con id = {0} " + blogId, postId);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la editorial");
    }
    
     /**
     * Remplazar books de una editorial
     *
     * @param posts Lista de libros que serán los de la editorial.
     * @param blogId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<PostEntity> replacePosts(Long blogId, List<PostEntity> posts) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", blogId);
        BlogEntity editorialEntity = editorialPersistence.find(blogId);
        List<PostEntity> bookList = bookPersistence.findAll();
        for (PostEntity book : bookList) {
            if (posts.contains(book)) {
                book.setBlog(editorialEntity);
            } else if (book.getBlog() != null && book.getBlog().equals(editorialEntity)) {
                book.setBlog(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", blogId);
        return posts;
    }
    
}
