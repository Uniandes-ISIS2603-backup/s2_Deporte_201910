/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.CampeonatoDTO;
import co.edu.uniandes.csw.deporte.ejb.CampeonatoLogic;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.MediaType;

/**
*
* @author Juan Camilo Garcia
*/
@Path("campeonato")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped

public class CampeonatoResource {

@Inject
private CampeonatoLogic campeonatoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.


private static final String EXC = "El recurso /campeonato/";

private static final String EXCE= " no existe.";
/**
 * Crea un nuevo campeonato con la informacion que se recibe en el cuerpo de la
 * petición y se regresa un objeto identico con un id auto-generado por la
 * base de datos.
 *
 * @param campeonato {@link CampeonatoDTO} - EL campeonato que se desea guardar.
 * @return JSON {@link CampeonatoDTO} - El campeonato guardado con el atributo id
 * autogenerado.
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 */
@POST
public CampeonatoDTO createCampeonato(CampeonatoDTO pCampeonato)throws BusinessLogicException
{
    return new CampeonatoDTO(campeonatoLogic.createCampeonato(pCampeonato.toEntity()));
}
/**
 * Busca el campeonato con el id asociado recibido en la URL y lo devuelve.
 *
 * @param campeonatoId Identificador del campeonato que se esta buscando. Este debe
 * ser una cadena de dígitos.
 * @return JSON {@link CampeonatoDTO} - El campeonato buscado
 * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 * Error de lógica que se genera cuando no se encuentra el libro.
 */

@GET
@Path("{campeonatoId: \\d+}")
public CampeonatoDTO getCampeonato(@PathParam("campeonatoId") Long campeonatoId)
{
    CampeonatoEntity campeonatoEntity = campeonatoLogic.getCampeonato(campeonatoId);
    if(campeonatoEntity == null)
    {
         throw new WebApplicationException(EXC + campeonatoId + EXCE, 404);
    }
    return new CampeonatoDTO(campeonatoEntity);

}

/**
 * Busca y devuelve todos los campeonatos que existen en la aplicacion.
 *
 * @return JSONArray {@link CampeonatoDTO} - Los libros encontrados en la
 * aplicación. Si no hay ninguno retorna una lista vacía.
 */
@GET
public List<CampeonatoDTO> getBlogs() {
    return listEntity2DetailDTO(campeonatoLogic.getCampeonatos());
}

/**
 * Actualiza el campeonato con el id recibido en la URL con la información que se
 * recibe en el cuerpo de la petición.
 *
 * @param campeonatoId Identificador del campeonato que se desea actualizar. Este debe
 * ser una cadena de dígitos.
 * @param campeonato {@link CampeonatoDTO} El campeonato que se desea guardar.
 * @return JSON {@link CampeonatoDTO} - El libro guardada.
 * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 * Error de lógica que se genera cuando no se encuentra el campeonato a
 * actualizar.
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 * Error de lógica que se genera cuando no se puede actualizar el campeonato.
 */
@PUT
@Path("{campeonatoId: \\d+}")
public CampeonatoDTO updateCampeonato(@PathParam("campeonatoId")Long campeonatoId, CampeonatoDTO pCampeonato)throws BusinessLogicException
{
    pCampeonato.setId(campeonatoId);
    if(campeonatoLogic.getCampeonato(campeonatoId) == null)
    {
        throw new  WebApplicationException(EXC + campeonatoId + EXCE, 404);
    }
    return new CampeonatoDTO(campeonatoLogic.updateCampeonato(campeonatoId, pCampeonato.toEntity()));
}

/**
 * Borra el campeonato con el id asociado recibido en la URL.
 *
 * @param campeonatoId Identificador del libro que se desea borrar. Este debe ser
 * una cadena de dígitos.
 * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 * Error de lógica que se genera cuando no se encuentra el campeonato.
 */
@DELETE
@Path("{campeonatoId: \\d+}")
public void deleteCampeonato(@PathParam("campeonatoId") Long campeonatoId) throws BusinessLogicException
{
 CampeonatoEntity entity = campeonatoLogic.getCampeonato(campeonatoId);
 if(entity == null)
 {
     throw new WebApplicationException(EXC + campeonatoId + EXCE, 404);
 }
 campeonatoLogic.deleteCampeonato(campeonatoId);
}

 public List<CampeonatoDTO> listEntity2DetailDTO(List<CampeonatoEntity> entityList) {
    List<CampeonatoDTO> list = new ArrayList<>();
    for (CampeonatoEntity entity : entityList) {
        list.add(new CampeonatoDTO(entity));
    }
    return list;
}
}
