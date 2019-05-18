/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.BlogPersistence;
import co.edu.uniandes.csw.deporte.persistence.CampeonatoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class CampeonatoBlogLogic {
    
        private static final Logger LOGGER = Logger.getLogger(CampeonatoBlogLogic.class.getName());

         @Inject
    private BlogPersistence bookPersistence;

    @Inject
    private CampeonatoPersistence editorialPersistence;
   
    
       /**
     * Agregar un book a la editorial
     *
     * @param booksId El id libro a guardar
     * @param editorialsId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public BlogEntity addBlog(Long booksId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la editorial con id = {0}", editorialsId);
        CampeonatoEntity editorialEntity = editorialPersistence.find(editorialsId);
        BlogEntity bookEntity = bookPersistence.find(booksId);
        editorialEntity.setBlog(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", editorialsId);
        return bookEntity;
    }
    
    /**
     * Remplazar la editorial de un book.
     *
     * @param booksId id del libro que se quiere actualizar.
     * @param editorialsId El id de la editorial que se será del libro.
     * @return el nuevo libro.
     */
    public CampeonatoEntity replaceBlog(Long booksId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar blog con id = {0}", booksId);
        CampeonatoEntity editorialEntity = editorialPersistence.find(editorialsId);
        BlogEntity bookEntity = bookPersistence.find(booksId);
        editorialEntity.setBlog(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar blog con id = {0}", bookEntity.getId());
        return editorialEntity;
    }
    
    /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param booksId El libro que se desea borrar de la editorial.
     */
    public void removeBlog(Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el campeonato del blog con id = {0}", booksId);
        CampeonatoEntity bookEntity = editorialPersistence.find(booksId);
        BlogEntity editorialEntity = bookEntity.getBlog();
        bookEntity.setBlog(null);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del libro con id = {0}", bookEntity.getId());
    }
    
     /**
     * Retorna un book asociado a una editorial
     *
     * @param editorialsId El id de la editorial a buscar.
     * @param booksId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public BlogEntity getBlog(Long editorialsId, Long booksId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la editorial con id = " + editorialsId, booksId);
         BlogEntity books = bookPersistence.find(editorialsId);
         
 if(books == null)
 {
        throw new BusinessLogicException("El libro no está asociado a la editorial");
 }
    return books;
    }

}
