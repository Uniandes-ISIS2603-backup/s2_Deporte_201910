/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.persistence;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
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
public class CanchaPersistence {

    public static final Logger LOGGER = Logger.getLogger(CanchaPersistence.class.getName());

    @PersistenceContext(unitName = "deportePU")
    protected EntityManager em;
    
    public CanchaEntity create(CanchaEntity canchaEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva cancha");
        em.persist(canchaEntity);
        //TypedQuery query = em.createQuery("update from CanchaEntity set propietario_id="+canchaEntity.getPropietario().getId()+" where id="+canchaEntity.getId(), CanchaEntity.class);
        //query.executeUpdate();
        LOGGER.log(Level.INFO, "Saliendo de crear una cancha nueva");
        return canchaEntity;
    }

    public CanchaEntity find(Long canchaId) {
        return em.find(CanchaEntity.class, canchaId);
    }

    public List<CanchaEntity> findAll() {

        TypedQuery<CanchaEntity> query = em.createQuery("select u from CanchaEntity u", CanchaEntity.class);
        return query.getResultList();
    }

    public CanchaEntity findByDireccion(String direccion) {

        TypedQuery<CanchaEntity> query = em.createQuery("select e from CanchaEntity e where e.direccion = :direccion", CanchaEntity.class);

        query = query.setParameter("direccion", direccion);
        CanchaEntity resul;
        List<CanchaEntity> sameDireccion = query.getResultList();
        if (sameDireccion == null) {
            resul = null;
        } else if (sameDireccion.isEmpty()) {
            resul = null;
        } else {
            resul = sameDireccion.get(0);
        }
        return resul;
    }

    /**
     * Actualiza una cancha.
     *
     * @param canchaEntity: el cancha que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una cancha con los cambios aplicados.
     */
    public CanchaEntity update(CanchaEntity canchaEntity) {
        LOGGER.log(Level.INFO, "Actualizando el libro con id={0}", canchaEntity.getId());
        return em.merge(canchaEntity);
    }

    /**
     *
     * Borra una cancha de la base de datos recibiendo como argumento el id de
     * la cancha
     *
     * @param canchasId: id correspondiente a la editorial a borrar.
     */
    public void delete(Long canchasId) {
        LOGGER.log(Level.INFO, "Borrando cancha con id = {0}", canchasId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        CanchaEntity entity = em.find(CanchaEntity.class, canchasId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la cancha con id = {0}", canchasId);
    }
}
