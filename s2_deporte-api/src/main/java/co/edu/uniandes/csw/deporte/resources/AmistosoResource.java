/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.AmistosoDTO;
import co.edu.uniandes.csw.deporte.dtos.EntrenamientoDTO;
import co.edu.uniandes.csw.deporte.ejb.AmistosoLogic;
import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
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
 * @author Nicolas De la Hoz
 */

@Path("amistosos")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped
public class AmistosoResource {
    private static final Logger LOGGER = Logger.getLogger(AmistosoResource.class.getName());
   
    @Inject
    private AmistosoLogic logica;
    
    
    @POST
    public AmistosoDTO createAmistoso(AmistosoDTO amistoso) throws BusinessLogicException{
        AmistosoEntity amistosoEntity=amistoso.toEntity();
        amistosoEntity= logica.createAmistoso(amistosoEntity);
        return new AmistosoDTO(amistosoEntity);
    }
    
    @PUT
    public AmistosoDTO modifyAmistoso(AmistosoDTO amistoso){
        return amistoso;
    }
    
    @DELETE
    @Path("{amistosoId: \\d+}")
    public void deleteAmistoso(@PathParam("amistosoId") Long amistosoId) throws WebApplicationException, BusinessLogicException{
        AmistosoEntity entidad=logica.find(amistosoId);
        if(entidad==null){
            throw new WebApplicationException("Amistoso con id: " + amistosoId + " no existe", 404);
        }
        logica.delete(amistosoId);
    }
    
    @GET
    @Path("{amistosoId: \\d+}")
    public AmistosoDTO getAmistoso(@PathParam("amistosoId") Long amistosoId) throws WebApplicationException, BusinessLogicException{
        AmistosoEntity entidad=logica.find(amistosoId);
        if(entidad==null){
            throw new WebApplicationException("Amistoso con id: " + amistosoId + " no existe", 404);
        }
        return new AmistosoDTO(entidad);
    }
    
        /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AmistosoDTO> getAmistoso() {
        LOGGER.info("CanchaResource getCancha: input: void");
        List<AmistosoDTO> listaAmistosos = listEntity2DetailDTO(logica.getAmistosos());
        LOGGER.log(Level.INFO, "CanchaResource getCancha: output: {0}", listaAmistosos);
        return listaAmistosos;
    }
    
        /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<AmistosoDTO> listEntity2DetailDTO(List<AmistosoEntity> entityList) {
        List<AmistosoDTO> list = new ArrayList<>();
        for (AmistosoEntity entity : entityList) {
            list.add(new AmistosoDTO(entity));
        }
        return list;
    }
}