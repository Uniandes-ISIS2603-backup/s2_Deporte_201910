/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @equipo estudiante
 */
@Stateless
public class EquipoPersistence 
{
    public static final Logger LOGGER = java.util.logging.Logger.getLogger(EquipoPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param EquipoEntity objeto equipo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EquipoEntity create(EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Creando un equipo nuevo");
        em.persist(equipoEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return equipoEntity;
    }

    /**
     * Devuelve todos losequipos de la base de datos.
     *
     * @return una lista con todos los equipos que encuentre en la base de datos,
     * "select u from EquipoEntity u" es como un "select * from EquipoEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<EquipoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los equipos");
        TypedQuery query = em.createQuery("select u from EquipoEntity u", EquipoEntity.class);
    return query.getResultList();
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param EquiposId: id correspondiente al equipo buscado.
     * @return un equipo.
     */
    public EquipoEntity find(Long equiposId) {
        LOGGER.log(Level.INFO, "Consultando el equipo con id={0}", equiposId);
        return em.find(EquipoEntity.class, equiposId);
    }

    /**
     * Actualiza un equipo.
     *
     * @param EquipoEntity: el equipo que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un equipo con los cambios aplicados.
     */
    public EquipoEntity update(EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el equipo con id={0}", equipoEntity.getId());
        return em.merge(equipoEntity);
    }

    /**
     *
     * Borra un equipo de la base de datos recibiendo como argumento el id del
     * equipo
     *
     * @param EquiposId: id correspondiente al equipo a borrar.
     */
    public void delete(Long equiposId) {
        LOGGER.log(Level.INFO, "Borrando el equipo con id={0}", equiposId);
        EquipoEntity equipoEntity = em.find(EquipoEntity.class, equiposId);
        em.remove(equipoEntity);
    }
}
