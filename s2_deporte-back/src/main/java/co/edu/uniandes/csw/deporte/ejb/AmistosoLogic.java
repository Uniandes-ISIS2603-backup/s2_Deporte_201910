/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.AmistosoPersistence;
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
public class AmistosoLogic {
    
    public static final Logger LOGGER = Logger.getLogger(AmistosoLogic.class.getName());
    @Inject
    public AmistosoPersistence persistence;
    
    public AmistosoEntity createAmistoso(AmistosoEntity amistoso) throws BusinessLogicException{
        LOGGER.log(Level.INFO,"Empieza el proceso de crear un amistoso");
        /*if(amistoso.getPartido()==null){
            throw new BusinessLogicException("Todo amistoso debe tener un partido asociado");
        }
        if(amistoso.getReserva()==null){
            throw new BusinessLogicException("Todo amistoso debe tener una reserva asociada");
        }*/
        amistoso=persistence.create(amistoso);
        LOGGER.log(Level.INFO,"Termina el proceso de crear un amistoso");
        return amistoso;
    }

         /**
     * Devuelve todos lon campeonato
     * s que hay en la base de datos.
     *
     * @return Lista de entidades de tipn campeonato
     * .
     */
    public List<AmistosoEntity> getAmistosos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los campeonatos");
        List<AmistosoEntity> campeonatos = persistence.findAll();
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los campeonatos");
        return campeonatos;
    }

    public AmistosoEntity find(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO,"Empieza el proceso de buscar un amistoso");
        AmistosoEntity amistoso=persistence.find(id); 
        if(amistoso==null){
            throw new BusinessLogicException("No se pudo encontrar el amistoso");
        }
        LOGGER.log(Level.INFO,"Termina el proceso de buscar un amistoso");
        return amistoso;
    }

    public List<AmistosoEntity> findAll() {
        LOGGER.log(Level.INFO,"Empieza el proceso de buscar todos los amistosos");
        List<AmistosoEntity> amistosos=persistence.findAll();
        LOGGER.log(Level.INFO,"Termina el proceso de buscar todos los amistosos");
        return amistosos;
        
    }

    public void update(AmistosoEntity amistoso) {
        LOGGER.log(Level.INFO,"Empieza el proceso de actualizar un amistoso");
        persistence.update(amistoso);
        LOGGER.log(Level.INFO,"Termina el proceso de actualizar un amistoso");
        
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO,"Empieza el proceso de eliminar un amistoso");
        persistence.delete(id);
        LOGGER.log(Level.INFO,"Termina el proceso de eliminar un amistoso");
    }
}
