/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.BlogDTO;
import co.edu.uniandes.csw.deporte.dtos.BlogDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.BlogLogic;
import co.edu.uniandes.csw.deporte.ejb.CampeonatoBlogLogic;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CampeonatoBlogResource {
    
        private static final Logger LOGGER = Logger.getLogger(CampeonatoBlogResource.class.getName());

        @Inject
    private CampeonatoBlogLogic editorialBooksLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private BlogLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param  campeonatoId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param blogId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{booksId: \\d+}")
    public BlogDTO addBlog(@PathParam("campeonatoId") Long  campeonatoId, @PathParam("blogId") Long blogId) {
        LOGGER.log(Level.INFO, "CampeonatoBlogResource addBlog: input: campeonatoID: {0} , blogId: {1}", new Object[]{ campeonatoId, blogId});
        if (bookLogic.getBlog(blogId) == null) {
            throw new WebApplicationException("El recurso /blog/" + blogId + " no existe.", 404);
        }
        BlogDTO bookDTO = new BlogDTO(editorialBooksLogic.addBlog(blogId, campeonatoId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", bookDTO);
        return bookDTO;
    }
    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param campeonatoId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param blogId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     */
    @GET
    @Path("{blogId: \\d+}")
    public BlogDetailDTO getBlog(@PathParam("campeonatoId") Long campeonatoId, @PathParam("blogId") Long blogId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{campeonatoId, blogId});
        if (bookLogic.getBlog(blogId) == null) {
            throw new WebApplicationException("El recurso /campeonato/" + campeonatoId + "/blog/" + blogId + " no existe.", 404);
        }
        BlogDetailDTO bookDetailDTO = new BlogDetailDTO(editorialBooksLogic.getBlog(campeonatoId, blogId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }    
}
