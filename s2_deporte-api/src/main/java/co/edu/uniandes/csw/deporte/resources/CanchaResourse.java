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

    @POST
    public CanchaDTO createCancha(CanchaDTO cancha) throws BusinessLogicException {

        CanchaEntity canchaEntity = cancha.toEntity();

        CanchaEntity newCanchaEntity = canchaLogic.createCancha(canchaEntity);

        CanchaDetailDTO newCanchaDTO = new CanchaDetailDTO(newCanchaEntity);
        return newCanchaDTO;
    }

    @PUT
    public CanchaDTO modifyCancha(CanchaDTO cancha) {
        return cancha;
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
