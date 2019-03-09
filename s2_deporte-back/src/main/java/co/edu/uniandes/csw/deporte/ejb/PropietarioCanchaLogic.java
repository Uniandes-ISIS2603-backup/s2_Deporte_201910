/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import co.edu.uniandes.csw.deporte.persistence.CanchaPersistence;
import co.edu.uniandes.csw.deporte.persistence.PropietarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PropietarioCanchaLogic {

    private static final Logger LOGGER = Logger.getLogger(PropietarioCanchaLogic.class.getName());

    @Inject
    private CanchaPersistence canchaPersistence;

    @Inject
    private PropietarioPersistence propietarioPersistence;

    /**
     * Asocia un Cancha existente a un Author
     *
     * @param propietarioId Identificador de la instancia de Author
     * @param canchaId Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Author
     */
    public CanchaEntity addCancha(Long propietarioId, Long canchaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", propietarioId);
        PropietarioEntity authorEntity = propietarioPersistence.find(propietarioId);
        CanchaEntity canhcaEntity = canchaPersistence.find(canchaId);
        canhcaEntity.setPropietario(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", propietarioId);
        return canchaPersistence.find(canchaId);
    }
}
