/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.PartidoPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @partido estudiante
 */
@Stateless
public class PartidoLogic 
{
    public static final Logger LOGGER = Logger.getLogger(PartidoLogic.class.getName());
    
    @Inject
    public PartidoPersistence partidoPersistence;
    
    public PartidoEntity createPartido(PartidoEntity partidoEntity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del partido");
        if(partidoEntity.getFecha().before(new Date()))
        {
            throw new BusinessLogicException("La fecha del partido ya pasó");
        }
        if(partidoEntity.getEquipos().size()>2)
        {
            throw new BusinessLogicException("No es la cantidad correcta de equipos");
        }
        if(partidoEntity.getEquipos().size()==2&&(partidoEntity.getEquipos().get(0).getId()==partidoEntity.getEquipos().get(1).getId()))
        {
            throw new BusinessLogicException("Los equipos están repetidos");
        }
        if(partidoEntity.getPuntaje().size()==2 && (partidoEntity.getPuntaje().get(0)<0||partidoEntity.getPuntaje().get(1)<0))
        {
            throw new BusinessLogicException("El partido tiene puntaje negativo");
        }
        if(getPartido(partidoEntity.getId())!=null)
        {
            throw new BusinessLogicException("El partido ya existe");
        }
        PartidoEntity newPartidoEntity = partidoPersistence.create(partidoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del partido");
        return newPartidoEntity;
    }
    
    public List<PartidoEntity> getPartidos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los partidos");
        List<PartidoEntity> lista = partidoPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los partidos");
        return lista;
    }
    
    public PartidoEntity getPartido(Long partidosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el partido con id = {0}", partidosId);
        PartidoEntity partidoEntity = partidoPersistence.find(partidosId);
        if (partidoEntity == null) {
            LOGGER.log(Level.SEVERE, "El partido con el id = {0} no existe", partidosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el partido con id = {0}", partidosId);
        return partidoEntity;
    }
    
    public PartidoEntity updatePartido(Long partidosId, PartidoEntity partidoEntity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el partido con id = {0}", partidosId);
        if(partidoEntity.getFecha().before(new Date()))
        {
            throw new BusinessLogicException("La fecha del partido ya pasó");
        }
        if(partidoEntity.getEquipos().size()>2)
        {
            throw new BusinessLogicException("No es la cantidad correcta de equipos");
        }
        if(partidoEntity.getEquipos().size()==2&&partidoEntity.getEquipos().get(0).getId()==partidoEntity.getEquipos().get(1).getId())
        {
            throw new BusinessLogicException("Los equipos están repetidos");
        }
        if(partidoEntity.getPuntaje().size()==2 && (partidoEntity.getPuntaje().get(0)<0||partidoEntity.getPuntaje().get(1)<0))
        {
            throw new BusinessLogicException("El partido tiene puntaje negativo");
        }
        PartidoEntity newpartidoEntity = partidoPersistence.update(partidoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el partido con id = {0}", partidosId);
        return newpartidoEntity;
    }
    
    public void deletePartido(Long partidosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el partido con id = {0}", partidosId);
        //hacer lo que se involucra con equipos
        partidoPersistence.delete(partidosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el partido con id = {0}", partidosId);
    }
}
