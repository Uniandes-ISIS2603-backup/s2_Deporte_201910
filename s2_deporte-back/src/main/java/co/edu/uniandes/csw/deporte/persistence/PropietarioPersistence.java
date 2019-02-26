/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class PropietarioPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(PropietarioPersistence.class.getName());
    
    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;

    public PropietarioEntity create(PropietarioEntity propietarioEntity) {

        em.persist(propietarioEntity);
        return propietarioEntity;
    }

    public PropietarioEntity find(Long propietarioId) {
        return em.find(PropietarioEntity.class, propietarioId);
    }

    public List<PropietarioEntity> findAll() {

        TypedQuery<PropietarioEntity> query = em.createQuery("select u from PropietarioEntity u", PropietarioEntity.class);
        return query.getResultList();
    }

       public PropietarioEntity findByName(String nombre) {

        TypedQuery<PropietarioEntity> query = em.createQuery("select e from PropietarioEntity e where e.nombre = :nombre", PropietarioEntity.class);

        query = query.setParameter("nombre", nombre);
        PropietarioEntity resul;
        List<PropietarioEntity> sameName = query.getResultList();
        if (sameName == null) {
            resul = null;
        } else if (sameName.isEmpty()) {
            resul = null;
        } else {
            resul = sameName.get(0);
        }
        return resul;
    }

    /**
     *
     * Borra una cancha de la base de datos recibiendo como argumento el id
     * de la cancha
     *
     * @param propietariosId: id correspondiente a la editorial a borrar.
     */
   public void delete(Long propietariosId) {
        LOGGER.log(Level.INFO, "Borrando propietario con id = {0}", propietariosId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        PropietarioEntity entity = em.find(PropietarioEntity.class, propietariosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
          em.remove(entity);
          LOGGER.log(Level.INFO, "Saliendo de borrar la propietario con id = {0}", propietariosId);
      }
}
