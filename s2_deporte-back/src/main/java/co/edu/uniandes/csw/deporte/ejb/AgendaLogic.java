/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.AgendaPersistence;
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
public class AgendaLogic {
    
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(AgendaLogic.class.getName());
    
    @Inject
    private AgendaPersistence persistence;
    
    public AgendaEntity create(AgendaEntity agenda) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicio proceso de creacion de Agenda");
        if(agenda.getCancha() == null)
        {
            throw new BusinessLogicException("La agenda no esta asignada a ninguna cancha");
        }
        
        agenda.setDia(1);
        
        agenda = persistence.create(agenda);
        LOGGER.log(Level.INFO, "Termino proceso de creacion de Agenda");
        return agenda;
    }
    
    public List<AgendaEntity> findAll() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las agendas");
        List<AgendaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las agendas");
        return lista;
    }
    
    public List<AgendaEntity> findAgendasPorCancha(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las agendas de la cancha con id: {0} ", id);
        List<AgendaEntity> lista = persistence.findAgendasPorCancha(id);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las agendas de la cancha con id: {0} ", id);
        return lista;
    }
    
    public AgendaEntity find(Long agendaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la agenda con id = {0}", agendaId);
        AgendaEntity agenda = persistence.find(agendaId);
        
        if (agenda == null) {
            LOGGER.log(Level.SEVERE, "La agenda con el id = {0} no existe", agendaId);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar la agenda con id = {0}", agendaId);
        return agenda;
    }
    
    public AgendaEntity update(Long id, AgendaEntity agendaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la agenda con id = {0}", agendaEntity.getId());
        AgendaEntity agenda = persistence.update(id, agendaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la agenda con id = {0}", agendaEntity.getId());
        return agenda;
    }
    
    public void delete(Long agendaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la agenda con id = {0}", agendaId);
        persistence.delete(agendaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrarla agenda con id = {0}", agendaId);
    }
    
}
