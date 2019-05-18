/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
import co.edu.uniandes.csw.deporte.persistence.PostPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PostBlogLogic {
    private static final Logger LOGGER = Logger.getLogger(PostBlogLogic.class.getName());

    @Inject
    private PostPersistence bookPersistence;

    @Inject
    private BlogPersistence editorialPersistence;
    
    /**
     * Remplazar la editorial de un book.
     *
     * @param postId id del libro que se quiere actualizar.
     * @param blogId El id de la editorial que se será del libro.
     * @return el nuevo libro.
     */
    public PostEntity replaceBlog(Long postId, Long blogId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", postId);
        BlogEntity editorialEntity = editorialPersistence.find(blogId);
        PostEntity bookEntity = bookPersistence.find(postId);
        bookEntity.setBlog(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", bookEntity.getId());
        return bookEntity;
    }
     /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param postId El libro que se desea borrar de la editorial.
     */
    public void removeBlog(Long postId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Editorial del libro con id = {0}", postId);
        PostEntity bookEntity = bookPersistence.find(postId);
        BlogEntity editorialEntity = editorialPersistence.find(bookEntity.getBlog().getId());
        bookEntity.setBlog(null);
        editorialEntity.getPosts().remove(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del libro con id = {0}", bookEntity.getId());
    }
    
}
