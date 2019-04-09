/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PropietarioDTO;
import co.edu.uniandes.csw.deporte.dtos.PropietarioDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.PropietarioLogic;
import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author Santiago Serrano
 */
@Path("propietarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PropietarioResourse {

    private static final Logger LOGGER = Logger.getLogger(PropietarioResourse.class.getName());

    @Inject
    private PropietarioLogic propietarioLogic;

    /**
     * Crea un nuevo propietario con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param propietario {@link PropietarioDTO} - EL propietario que se desea
     * guardar.
     * @return JSON {@link PropietarioDTO} - El propietario guardado con el
     * atributo id autogenerado.
     * @throws co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException
     */
    @POST
    public PropietarioDTO createPropietario(PropietarioDTO propietario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AuthorResource createPropietario: input: {0}", propietario);
        PropietarioDTO propietarioDTO = new PropietarioDTO(propietarioLogic.createPropietrio(propietario.toEntity()));
        LOGGER.log(Level.INFO, "AuthorResource createPropietario: output: {0}", propietarioDTO);
        return propietarioDTO;
    }

    /**
     * Busca el propietario con el id asociado recibido en la URL y lo devuelve.
     *
     * @param propietarioId Identificador del autor que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link PropietarioDetailDTO} - El propietario buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el propietario.
     */
    @GET
    @Path("{propietarioId: \\d+}")
    public PropietarioDetailDTO getPropietario(@PathParam("propietarioId") Long propietarioId) {
        LOGGER.log(Level.INFO, "PropietarioResource getAuthor: input: {0}", propietarioId);
        PropietarioEntity propietarioEntity = propietarioLogic.getPropietario(propietarioId);
        if (propietarioEntity == null) {
            throw new WebApplicationException("El recurso /propietario/" + propietarioId + " no existe.", 404);
        }
        PropietarioDetailDTO detailDTO = new PropietarioDetailDTO(propietarioEntity);
        LOGGER.log(Level.INFO, "PropietarioResource getPropietario: output: {0}", detailDTO);
        return detailDTO;
    }

    @GET
    public List<PropietarioDetailDTO> getPropietarios() {
        LOGGER.info("PropietarioResource getPropietario: input: void");
        List<PropietarioDetailDTO> listaPropietario = listEntity2DetailDTO(propietarioLogic.getPropietarios());
        LOGGER.log(Level.INFO, "CanchaResource getCancha: output: {0}", listaPropietario);
        return listaPropietario;
    }
    
    /**
     * Actualiza el propietario con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param propietarioId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param propietario {@link PropietarioDetailDTO} El propietario que se desea guardar.
     * @return JSON {@link PropietarioDetailDTO} - El propietario guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{propietarioId: \\d+}")
    public PropietarioDetailDTO updatePropietario(@PathParam("propietarioId") Long propietarioId, PropietarioDetailDTO propietario) {
        LOGGER.log(Level.INFO, "PropietarioResource updatePropietario: input: propietarioId: {0} , author: {1}", new Object[]{propietarioId, propietario});
        propietario.setId(propietarioId);
        if (propietarioLogic.getPropietario(propietarioId) == null) {
            throw new WebApplicationException("El recurso /authors/" + propietarioId + " no existe.", 404);
        }
        PropietarioDetailDTO detailDTO = new PropietarioDetailDTO(propietarioLogic.updatePropietario(propietarioId, propietario.toEntity()));
        LOGGER.log(Level.INFO, "AuthorResource updateAuthor: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el propietario con el id asociado recibido en la URL.
     *
     * @param propietarioId Identificador del propietario que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el propietario a
     * borrar.
     */
    @DELETE
    @Path("{propietarioId: \\d+}")
    public void deleteAuthor(@PathParam("propietarioId") Long propietarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AuthorResource deletePropietario: input: {0}", propietarioId);
        if (propietarioLogic.getPropietario(propietarioId) == null) {
            throw new WebApplicationException("El recurso /propietario/" + propietarioId + " no existe.", 404);
        }
        propietarioLogic.deletePropietario(propietarioId);
        LOGGER.info("PropietarioResource deletePropietario: output: void");
    }
    
        private List<PropietarioDetailDTO> listEntity2DetailDTO(List<PropietarioEntity> entityList) {
        List<PropietarioDetailDTO> list = new ArrayList<>();
        for (PropietarioEntity entity : entityList) {
            list.add(new PropietarioDetailDTO(entity));
        }
        return list;
    }
}
