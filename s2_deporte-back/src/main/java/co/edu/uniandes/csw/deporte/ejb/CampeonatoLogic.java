/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.CampeonatoPersistence;
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
public class CampeonatoLogic {
    private static final Logger LOGGER = Logger.getLogger(CampeonatoLogic.class.getName());

    @Inject
    private CampeonatoPersistence persistence;
    
    /**
     * Guardar un nuevo libro
     *
     * @param campeonatoEntity La entidad de tipo libro del nuevo libro a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     */
    public CampeonatoEntity createCampeonato(CampeonatoEntity campeonatoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del Campeonato");
        if( campeonatoEntity.getNombre() == null)
        {
            throw new BusinessLogicException("Debe tener un nombre");
        }
       if(persistence.findByName(campeonatoEntity.getNombre()) != null)
       {
           throw new BusinessLogicException("Ya existe un campeonato con ese nombre");

       }
               persistence.create(campeonatoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del campeonato");
        return campeonatoEntity;
    }
    
     /**
     * Devuelve todos los libros que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<CampeonatoEntity> getCampeonatos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los campeonatos");
        List<CampeonatoEntity> campeonatos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los campeonatos");
        return campeonatos;
    }
    
    /**
     * Busca un libro por ID
     *
     * @param campeonatosId El id del libro a buscar
     * @return El libro encontrado, null si no lo encuentra.
     */
    public CampeonatoEntity getCampeonato(Long campeonatosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el campeonato con id = {0}", campeonatosId);
        CampeonatoEntity campeonatoEntity = persistence.find(campeonatosId);
        if (campeonatoEntity == null) {
            LOGGER.log(Level.SEVERE, "El campeonato con el id = {0} no existe", campeonatosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el campeonato con id = {0}", campeonatosId);
        return campeonatoEntity;
    }
    
    /**
     * Actualizar un libro por ID
     *
     * @param campeonatosId El ID del libro a actualizar
     * @param campeonatoEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public CampeonatoEntity updateCampeonato(Long campeonatosId, CampeonatoEntity campeonatoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el campeonato con id = {0}", campeonatosId);
        if (!validateNombre(campeonatoEntity.getNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
        CampeonatoEntity newEntity = persistence.update(campeonatoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el campeonato con id = {0}", campeonatoEntity.getId());
        return newEntity;
    }
    
     private boolean validateNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
    }
     
     /**
     * Eliminar un libro por ID
     *
     * @param campeonatosId El ID del libro a eliminar
     * @throws BusinessLogicException si el libro tiene autores asociados
     */
    public void deleteCampeonato(Long campeonatosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el campeonato con id = {0}", campeonatosId);
        
        persistence.delete(campeonatosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el campeonato con id = {0}", campeonatosId);
    }


}
