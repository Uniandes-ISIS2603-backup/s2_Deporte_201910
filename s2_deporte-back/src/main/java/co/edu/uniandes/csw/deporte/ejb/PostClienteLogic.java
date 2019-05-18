/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.persistence.ClientePersistence;
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

public class PostClienteLogic {
 private static final Logger LOGGER = Logger.getLogger(PostClienteLogic.class.getName());

    @Inject
    private PostPersistence bookPersistence;

    @Inject
    private ClientePersistence editorialPersistence;
    
    /**
     * Remplazar la editorial de un book.
     *
     * @param postId id del libro que se quiere actualizar.
     * @param ClienteId El id de la editorial que se será del libro.
     * @return el nuevo libro.
     */
    public PostEntity replaceCliente(Long postId, Long ClienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", postId);
        ClienteEntity editorialEntity = editorialPersistence.find(ClienteId);
        PostEntity bookEntity = bookPersistence.find(postId);
        bookEntity.setCliente(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", bookEntity.getId());
        return bookEntity;
    }
     /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param postId El libro que se desea borrar de la editorial.
     */
    public void removeCliente(Long postId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Editorial del libro con id = {0}", postId);
        PostEntity bookEntity = bookPersistence.find(postId);
        ClienteEntity editorialEntity = editorialPersistence.find(bookEntity.getCliente().getId());
        bookEntity.setCliente(null);
        editorialEntity.getPosts().remove(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del libro con id = {0}", bookEntity.getId());
    }
    
}    

