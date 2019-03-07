/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.CanchaDTO;
import co.edu.uniandes.csw.deporte.dtos.CanchaDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.CanchaLogic;
import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Path("canchas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CanchaResourse {

    private static final Logger LOGGER = Logger.getLogger(CanchaResourse.class.getName());

    @Inject
    private CanchaLogic canchaLogic;

    /**
     * Crea una nueva cancha con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param cancha {@link CanchaDTO} - La cancha que se desea guardar.
     * @return JSON {@link CanchaDTO} - La cancha guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la cancha.
     */
    @POST
    public CanchaDTO createCancha(CanchaDTO cancha) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CanchaResource createCancha: input: {0}", cancha);
        CanchaDTO nuevoCanchaDTO = new CanchaDTO(canchaLogic.createCancha(cancha.toEntity()));
        LOGGER.log(Level.INFO, "CanchaResource createCancha: output: {0}", nuevoCanchaDTO);
        return nuevoCanchaDTO;
    }

    /**
     * Actualiza el libro con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param canchaId Identificador del libro que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param cancha {@link CanchaDTO} El cancha que se desea guardar.
     * @return JSON {@link CanchaDetailDTO} - El cancha guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el libro.
     */
    @PUT
    @Path("{canchaId: \\d+}")
    public CanchaDetailDTO updateCancha(@PathParam("canchaId") Long canchaId, CanchaDetailDTO cancha) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CanchaResource updateBook: input: id: {0} , book: {1}", new Object[]{canchaId, cancha});
        cancha.setId(canchaId);
        if (canchaLogic.getCancha(canchaId) == null) {
            throw new WebApplicationException("El recurso /canchas/" + canchaId + " no existe.", 404);
        }
        CanchaDetailDTO detailDTO = new CanchaDetailDTO(canchaLogic.updateCancha(canchaId, cancha.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO);
        return detailDTO;
    }

    @GET
    @Path("{canchaId: \\d+}")
    public CanchaDTO getCancha(@PathParam("canchaId") Long canchaId) throws WebApplicationException {
        CanchaEntity canchaEntity = canchaLogic.getCancha(canchaId);
        CanchaDetailDTO canchaDetailDTO = new CanchaDetailDTO(canchaEntity);
        return canchaDetailDTO;
    }

    @DELETE
    @Path("{canchaId: \\d+}")
    public void deleteCancha(@PathParam("canchaId") Long canchaId) {

        canchaLogic.deleteCancha(canchaId);

    }
}
