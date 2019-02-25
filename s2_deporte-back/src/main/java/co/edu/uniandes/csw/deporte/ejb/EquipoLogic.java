/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.EquipoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class EquipoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(EquipoLogic.class.getName());
    @Inject
    private EquipoPersistence equipoPersistence;
    
    public EquipoEntity createEquipo(EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del equipo");
        EquipoEntity newEquipoEntity = equipoPersistence.create(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del equipo");
        return newEquipoEntity;
    }
    
    public List<EquipoEntity> getEquipos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los equipos");
        List<EquipoEntity> lista = equipoPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los equipos");
        return lista;
    }
    
    public EquipoEntity getequipo(Long equiposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el equipo con id = {0}", equiposId);
        EquipoEntity equipoEntity = equipoPersistence.find(equiposId);
        if (equipoEntity == null) {
            LOGGER.log(Level.SEVERE, "El equipo con el id = {0} no existe", equiposId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el equipo con id = {0}", equiposId);
        return equipoEntity;
    }
    
    public EquipoEntity updateequipo(Long equiposId, EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el equipo con id = {0}", equiposId);
        EquipoEntity newequipoEntity = equipoPersistence.update(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el equipo con id = {0}", equiposId);
        return newequipoEntity;
    }
    
    public void deleteequipo(Long equiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el equipo con id = {0}", equiposId);
        //hacer lo que se involucra con equipos
        equipoPersistence.delete(equiposId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el equipo con id = {0}", equiposId);
    }
}
