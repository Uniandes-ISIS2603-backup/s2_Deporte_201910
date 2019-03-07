/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
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
public class PartidoPersistence 
{
    public static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(EquipoPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    /**
     * Crea un partido en la base de datos
     *
     * @param partidoEntity objeto partido que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PartidoEntity create(PartidoEntity partidoEntity) {
        LOGGER.log(Level.INFO, "Creando un partido nuevo");
        em.persist(partidoEntity);
        LOGGER.log(Level.INFO, "Partido creado");
        return partidoEntity;
    }

    /**
     * Devuelve todos los partidos de la base de datos.
     *
     * @return una lista con todos los equipos que encuentre en la base de datos
     */
    public List<PartidoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los partidos");
        TypedQuery query = em.createQuery("select u from PartidoEntity u", PartidoEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun equipo con el id que se envía de argumento
     *
     * @param partidoId: id correspondiente al equipo buscado.
     * @return un equipo.
     */
    public PartidoEntity find(Long partidosId) 
    {
        LOGGER.log(Level.INFO, "Consultando el partido con id={0}", partidosId);
        return em.find(PartidoEntity.class, partidosId);
    }

    /**
     * Actualiza un partido.
     *
     * @param partidoEntity: el partido que viene con los nuevos cambios. 
     * @return un partido con los cambios aplicados.
     */
    public PartidoEntity update(PartidoEntity partidoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el partido con id={0}", partidoEntity.getId());
        return em.merge(partidoEntity);
    }

    /**
     * Borra un partido de la base de datos recibiendo como argumento el id del partido
     * @param partidosId: id correspondiente al equipo a borrar.
     */
    public void delete(Long partidosId) {

        LOGGER.log(Level.INFO, "Borrando el partido con id={0}", partidosId);
        PartidoEntity partidoEntity = em.find(PartidoEntity.class, partidosId);
        em.remove(partidoEntity);
    }
}
