/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.CanchaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Santiago Serrano
 */
@Stateless
public class CanchaLogic {

    @Inject
    private CanchaPersistence persistence;

    private static final Logger LOGGER = Logger.getLogger(CanchaLogic.class.getName());

    public CanchaEntity createCancha(CanchaEntity cancha) throws BusinessLogicException {

        if (persistence.findByDireccion(cancha.getDireccion()) != null) {
            throw new BusinessLogicException("Ya existe la cancha con la direccion \"" + cancha.getDireccion() + "\"");
        }

        cancha = persistence.create(cancha);
        return cancha;
    }

    public CanchaEntity getCancha(Long canchaId)throws WebApplicationException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar con id = {0}", canchaId);
        
        CanchaEntity consulta = persistence.find(canchaId);
        if(consulta==null){
            throw new WebApplicationException("La cancha con el id: "+canchaId+" no existe");
        }
        return consulta;
    }
    
    /**
     * Borrar una cancha
     *
     * @param canchasId: id de la cancha a borrar
     */
    public void deleteCancha(Long canchasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la cancha con id = {0}", canchasId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(canchasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la cancha con id = {0}", canchasId);
    }
}
