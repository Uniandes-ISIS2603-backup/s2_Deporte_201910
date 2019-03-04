/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.EntrenamientoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.EntrenamientoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas De la Hoz
 */
@Stateless
public class EntrenamientoLogic {
     private static final Logger LOGGER = Logger.getLogger(EntrenamientoLogic.class.getName());
    @Inject
    private EntrenamientoPersistence persistence;
    
    public EntrenamientoEntity createEntrenamiento(EntrenamientoEntity entrenamiento) throws BusinessLogicException{
        LOGGER.log(Level.INFO,"Empieza el proceso de crear un entrenamiento");
        /*if(entrenamiento.getEquipo()==null){
            throw new BusinessLogicException("Todo Entrenamiento debe tener un partido asociado");
        }
        if(amistoso.getReserva()==null){
            throw new BusinessLogicException("Todo Entrenamiento debe tener una reserva asociada");
        }*/
        entrenamiento=persistence.create(entrenamiento);
        LOGGER.log(Level.INFO,"Termina el proceso de crear un entrenamiento");
        return entrenamiento;
    }



    public EntrenamientoEntity find(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO,"Empieza el proceso de buscar un entrenmiento");
        EntrenamientoEntity entrenamiento=persistence.find(id); 
        if(entrenamiento==null){
            throw new BusinessLogicException("No se pudo encontrar el entrenamiento");
        }
        LOGGER.log(Level.INFO,"Termina el proceso de buscar un entrenamiento");
        return entrenamiento;
    }

    public List<EntrenamientoEntity> findAll() {
        LOGGER.log(Level.INFO,"Empieza el proceso de buscar todos los entrenamientos");
        List<EntrenamientoEntity> entrenamientos=persistence.findAll();
        LOGGER.log(Level.INFO,"Termina el proceso de buscar todos los entrenamientos");
        return entrenamientos;
        
    }

    public void update(EntrenamientoEntity entrenamiento) {
        LOGGER.log(Level.INFO,"Empieza el proceso de actualizar un entrenamiento");
        persistence.update(entrenamiento);
        LOGGER.log(Level.INFO,"Termina el proceso de actualizar un entrenamiento");
        
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO,"Empieza el proceso de eliminar un entrenamiento");
        persistence.delete(id);
        LOGGER.log(Level.INFO,"Termina el proceso de eliminar un entrenmiento");
    }
}
