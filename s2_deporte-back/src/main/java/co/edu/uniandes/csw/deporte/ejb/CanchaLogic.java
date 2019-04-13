/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.CanchaPersistence;
import co.edu.uniandes.csw.deporte.persistence.PropietarioPersistence;
import java.util.List;
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
    @Inject
    private PropietarioPersistence pl;

    private static final Logger LOGGER = Logger.getLogger(CanchaLogic.class.getName());

    public CanchaEntity createCancha(CanchaEntity cancha) throws BusinessLogicException {

        if (persistence.findByDireccion(cancha.getDireccion()) != null) {
            throw new BusinessLogicException("Ya existe la cancha con la direccion \"" + cancha.getDireccion() + "\"");
        }
        //PropietarioEntity p=pl.findByName(cancha.getPropietario().getNombre());
        //cancha.setPropietario(p);
        cancha = persistence.create(cancha);
        return cancha;
    }

    public CanchaEntity getCancha(Long canchaId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar con id = {0}", canchaId);

        CanchaEntity consulta = persistence.find(canchaId);
        if (consulta == null) {
            throw new WebApplicationException("La cancha con el id: " + canchaId + " no existe");
        }
        return consulta;
    }

    /**
     * Devuelve todos los libros que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<CanchaEntity> getCanchas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros");
        List<CanchaEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los libros");
        return books;
    }

    /**
     * Actualizar una cancha por ID
     *
     * @param canchaId El ID de la cancha a actualizar
     * @param canchaEntity La entidad de la cancha con los cambios deseados
     * @return La entidad de la cancha luego de actualizarla
     * @throws BusinessLogicException
     */
    public CanchaEntity updateCancha(Long canchaId, CanchaEntity canchaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el libro con id = {0}", canchaId);
        CanchaEntity newEntity = persistence.update(canchaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el libro con id = {0}", canchaEntity.getId());
        return newEntity;
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
