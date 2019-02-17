/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class EquipoPersistence 
{
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(EquipoPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    /**
     * Crea un equipo en la base de datos
     *
     * @param equipoEntity objeto equipo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EquipoEntity create(EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Creando un equipo nuevo");
        em.persist(equipoEntity);
        LOGGER.log(Level.INFO, "Equipo creado");
        return equipoEntity;
    }

    /**
     * Devuelve todos los equipos de la base de datos.
     *
     * @return una lista con todos los equipos que encuentre en la base de datos
     */
    public List<EquipoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los equipos");
        TypedQuery query = em.createQuery("select u from EquipoEntity u", EquipoEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun equipo con el id que se envía de argumento
     *
     * @param equiposId: id correspondiente al equipo buscado.
     * @return un equipo.
     */
    public EquipoEntity find(Long equiposId) 
    {
        LOGGER.log(Level.INFO, "Consultando el equipo con id={0}", equiposId);
        return em.find(EquipoEntity.class, equiposId);
    }

    /**
     * Actualiza un equipo.
     *
     * @param equipoEntity: el equipo que viene con los nuevos cambios. 
     * @return un equipo con los cambios aplicados.
     */
    public EquipoEntity update(EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente con id={0}", equipoEntity.getId());
        return em.merge(equipoEntity);
    }

    /**
     * Borra un equipo de la base de datos recibiendo como argumento el id del equipo
     * @param equiposId: id correspondiente al equipo a borrar.
     */
    public void delete(Long equiposId) {

        LOGGER.log(Level.INFO, "Borrando el equipo con id={0}", equiposId);
        EquipoEntity equipoEntity = em.find(EquipoEntity.class, equiposId);
        em.remove(equipoEntity);
    }
}
