/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.ClientePersistence;
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

public class ClientePostsLogic {
    private static final Logger LOGGER = Logger.getLogger(ClientePostsLogic.class.getName());

    @Inject
    private PostPersistence bookPersistence;

    @Inject
    private ClientePersistence editorialPersistence;
    
     /**
     * Agregar un book a la editorial
     *
     * @param postId El id libro a guardar
     * @param clienteId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public PostEntity addPost(Long postId, Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la editorial con id = {0}", clienteId);
        ClienteEntity editorialEntity = editorialPersistence.find(clienteId);
        PostEntity bookEntity = bookPersistence.find(postId);
        bookEntity.setCliente(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", clienteId);
        return bookEntity;
    }
    
    /**
     * Retorna todos los books asociados a una editorial
     *
     * @param ClienteId El ID de la editorial buscada
     * @return La lista de libros de la editorial
     */
    public List<PostEntity> getPosts(Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la editorial con id = {0}", clienteId);
        return editorialPersistence.find(clienteId).getPosts();
    }
    
     /**
     * Retorna un book asociado a una editorial
     *
     * @param ClienteId El id de la editorial a buscar.
     * @param postId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public PostEntity getPost(Long clienteId, Long postId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la editorial con id = " + clienteId, postId);
        List<PostEntity> books = editorialPersistence.find(clienteId).getPosts();
        PostEntity bookEntity = bookPersistence.find(postId);
        int index = books.indexOf(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la editorial con id = " + clienteId, postId);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la editorial");
    }
    
     /**
     * Remplazar books de una editorial
     *
     * @param posts Lista de libros que serán los de la editorial.
     * @param clienteId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<PostEntity> replacePosts(Long clienteId, List<PostEntity> posts) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", clienteId);
        ClienteEntity editorialEntity = editorialPersistence.find(clienteId);
        List<PostEntity> bookList = bookPersistence.findAll();
        for (PostEntity book : bookList) {
            if (posts.contains(book)) {
                book.setCliente(editorialEntity);
            } else if (book.getCliente() != null && book.getCliente().equals(editorialEntity)) {
                book.setCliente(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", clienteId);
        return posts;
    }
}
