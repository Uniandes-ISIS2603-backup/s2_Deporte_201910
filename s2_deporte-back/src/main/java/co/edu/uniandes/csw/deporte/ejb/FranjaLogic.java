/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import co.edu.uniandes.csw.deporte.persistence.FranjaPersistence;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Barbosa
 */
@Stateless
public class FranjaLogic {
    
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(FranjaLogic.class.getName());
    
    @Inject
    private FranjaPersistence persistence;
    
    public FranjaEntity create(FranjaEntity franja) throws BusinessLogicException{
        
        if(franja.getAgenda() == null)
        {
            throw new BusinessLogicException("La franja debe pertenecer a una agenda");
        }
        if(franja.getHoraInicio() > 23 || franja.getHoraInicio() < 0)
        {
            throw new BusinessLogicException("La hora de inicio debe ser entre 0 y 23 horas");
        }
        if(franja.getHoraFin() > 23 || franja.getHoraFin() < 0)
        {
            throw new BusinessLogicException("La hora de fin debe ser entre 0 y 23 horas");
        }
        if(franja.getHoraInicio() >= franja.getHoraFin())
        {
            throw new BusinessLogicException("La hora de fin debe ser estrictamente mayor a la hora de inicio");
        }
        
        franja.setDuracionHoras(franja.getHoraFin()-franja.getHoraInicio());
        
        
        franja = persistence.create(franja);
        return franja;
    }
    
    public List<FranjaEntity> findAll() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las franjas");
        List<FranjaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las franjas");
        return lista;
    }
    
    public FranjaEntity find(Long franjaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la franja con id = {0}", franjaId);
        FranjaEntity franja = persistence.find(franjaId);
        
        if (franja == null) {
            LOGGER.log(Level.SEVERE, "La franja con el id = {0} no existe", franjaId);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar la franja con id = {0}", franjaId);
        return franja;
    }
    
    public List<FranjaEntity> findFranjasPorAgenda(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las franjas de la agenda con id: " + id);
        List<FranjaEntity> lista = persistence.findFranjasPorAgenda(id);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las franjas de la agenda con id: " + id);
        return lista;
    } 
    
    public FranjaEntity update( FranjaEntity franjaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la franja con id = {0}", franjaEntity.getId());
        FranjaEntity franja = persistence.update(franjaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la agenda con id = {0}", franjaEntity.getId());
        return franja;
    }
    
    public void delete(Long franjaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la franja con id = {0}", franjaId);
        persistence.delete(franjaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrarla franja con id = {0}", franjaId);
    }
}
