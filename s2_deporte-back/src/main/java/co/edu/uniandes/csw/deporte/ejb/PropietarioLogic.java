/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.PropietarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Serrano
 */
@Stateless
public class PropietarioLogic {
    
    @Inject
    private PropietarioPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(PropietarioLogic.class.getName());
    
    public PropietarioEntity createPropietrio (PropietarioEntity propietario)throws BusinessLogicException{
        
        if(persistence.findByName(propietario.getNombre())!= null){
            throw new BusinessLogicException("Ya existe el propietario con el nombre \""+propietario.getNombre());
        }
        
        propietario = persistence.create(propietario);
        
        return propietario;
    }
    
        /**
     * Borrar un propietario
     *
     * @param editorialsId: id de la editorial a borrar
     */
    public void deletePropietario(Long propietarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la propietario con id = {0}", propietarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(propietarioId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la propietario con id = {0}", propietarioId);
    }
}
