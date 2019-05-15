/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.PropietarioPersistence;
import java.util.List;
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

    public PropietarioEntity createPropietrio(PropietarioEntity propietario) throws BusinessLogicException {
        LOGGER.log(Level.INFO,"1");
        if (persistence.findByName(propietario.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe el propietario con el nombre \"" + propietario.getNombre());
        }
        propietario = persistence.create(propietario);
        return propietario;
    }

    /**
     * Obtiene los datos de una instancia de Propietario a partir de su ID.
     *
     * @param propietarioId Identificador de la instancia a consultar
     * @return Instancia de PropietarioEntity con los datos del Propietario consultado.
     */
    public PropietarioEntity getPropietario(Long propietarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el propietario con id = {0}", propietarioId);
        PropietarioEntity propietarioEntity = persistence.find(propietarioId);
        if (propietarioEntity == null) {
            LOGGER.log(Level.SEVERE, "La propietario con el id = {0} no existe", propietarioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el propietario con id = {0}", propietarioId);
        return propietarioEntity;
    }

    /**
     * Actualiza la información de una instancia de Propietario.
     *
     * @param propietarioId Identificador de la instancia a actualizar
     * @param propietarioEntity Instancia de PropietarioEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     */
    public PropietarioEntity updatePropietario(Long propietarioId, PropietarioEntity propietarioEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el propietario con id = {0}", propietarioId);
        PropietarioEntity newPropietarioEntity = persistence.update(propietarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el propietario con id = {0}", propietarioId);
        return newPropietarioEntity;
    }

    /**
     * Borrar un propietario
     *
     * @param propietarioId: id del propietario a borrar
     */
    public void deletePropietario(Long propietarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la propietario con id = {0}", propietarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(propietarioId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la propietario con id = {0}", propietarioId);
    }
    
    public List<PropietarioEntity> getPropietarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros");
        List<PropietarioEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los libros");
        return books;
    }
}
