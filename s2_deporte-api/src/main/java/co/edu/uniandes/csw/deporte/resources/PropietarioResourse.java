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
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("propietarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PropietarioResourse {
    
    private static final Logger LOGGER = Logger.getLogger(PropietarioResourse.class.getName());
    
    @Inject
    private PropietarioLogic propietarioLogic;
    
    @POST
    public PropietarioDTO createPropietario(PropietarioDTO propietario)throws BusinessLogicException{
        
        PropietarioEntity propietarioEntity = propietario.toEntity();

        PropietarioEntity newCanchaEntity = propietarioLogic.createPropietrio(propietarioEntity);

        PropietarioDetailDTO newPropietarioDTO = new PropietarioDetailDTO(newCanchaEntity);

        return newPropietarioDTO;
    }
      
    @PUT
    public PropietarioDTO modifyCancha(PropietarioDTO propietario){
        return propietario;
    }
    
    @DELETE
    public PropietarioDTO deleteCancha(PropietarioDTO propietario){
        return propietario;
    }
}
