/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.CanchaDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.CanchaLogic;
import co.edu.uniandes.csw.deporte.ejb.PropietarioCanchaLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Santiago Serrano
 */
@Path("CanchasPropietarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PropietarioCanchaResource {

    private static final Logger LOGGER = Logger.getLogger(PropietarioCanchaResource.class.getName());

    @Inject
    private PropietarioCanchaLogic propietarioCanchaLogic;

    @Inject
    private CanchaLogic canchaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un libro existente con un autor existente
     *
     * @param propietarioId El ID del autor al cual se le va a asociar el libro
     * @param canchaId El ID del libro que se asocia
     * @return JSON {@link BookDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{canchaId: \\d+}")
    public CanchaDetailDTO addCancha(@PathParam("propietarioId") Long propietarioId, @PathParam("canchaId") Long canchaId) {
        LOGGER.log(Level.INFO, "AuthorBooksResource addBook: input: authorsId {0} , booksId {1}", new Object[]{propietarioId, canchaId});
        if (canchaLogic.getCancha(canchaId) == null) {
            throw new WebApplicationException("El recurso /canhca/" + canchaId + " no existe.", 404);
        }
        CanchaDetailDTO detailDTO = new CanchaDetailDTO(propietarioCanchaLogic.addCancha(propietarioId, canchaId));
        LOGGER.log(Level.INFO, "AuthorBooksResource addBook: output: {0}", detailDTO);
        return detailDTO;
    }
}
