/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.ClienteDTO;
import co.edu.uniandes.csw.deporte.dtos.PostDTO;
import co.edu.uniandes.csw.deporte.ejb.ClienteLogic;
import co.edu.uniandes.csw.deporte.ejb.PostClienteLogic;
import co.edu.uniandes.csw.deporte.ejb.PostLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("posts/{postId: \\d+}/Cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostClienteResource {
    private static final Logger LOGGER = Logger.getLogger(PostClienteResource.class.getName());

    @Inject
    private PostLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PostClienteLogic bookEditorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ClienteLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias
    
    
    /**
     * Remplaza la instancia de Editorial asociada a un Book.
     *
     * @param postId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param editorial La editorial que se será del libro.
     * @return JSON {@link BookDetailDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial o el
     * libro.
     */
    @PUT
    public PostDTO replaceEditorial(@PathParam("postId") Long postId, ClienteDTO editorial) {
        LOGGER.log(Level.INFO, "BookEditorialResource replaceEditorial: input: booksId{0} , Editorial:{1}", new Object[]{postId, editorial});
        if (bookLogic.getPost(postId) == null) {
            throw new WebApplicationException("El recurso /post/" + postId + " no existe.", 404);
        }
        if (editorialLogic.getCliente(editorial.getId()) == null) {
            throw new WebApplicationException("El recurso /Cliente/" + editorial.getId() + " no existe.", 404);
        }
        PostDTO bookDetailDTO = new PostDTO(bookEditorialLogic.replaceCliente(postId, editorial.getId()));
        LOGGER.log(Level.INFO, "BookEditorialResource replaceEditorial: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }
}
