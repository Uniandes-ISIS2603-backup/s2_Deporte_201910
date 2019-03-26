/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import static co.edu.uniandes.csw.deporte.ejb.RepresentanteLogic.LOGGER;
import co.edu.uniandes.csw.deporte.entities.RepresentanteEntity;
import co.edu.uniandes.csw.deporte.entities.RepresentanteEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.RepresentantePersistence;
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
public class RepresentanteLogic 
{
    public static final Logger LOGGER = Logger.getLogger(RepresentanteLogic.class.getName());
    
    @Inject
    public RepresentantePersistence representantePersistence;

    public RepresentanteEntity createRepresentante(RepresentanteEntity representanteEntity)throws BusinessLogicException
    {
        System.out.println("Se empieza");
        if(representanteEntity.representa==null)
        {
            throw new BusinessLogicException("El representante no tiene equipo");
        }
        if(representanteEntity.representante==null)
        {
            throw new BusinessLogicException("El representa no tiene usuario");
        }
        if(!verificacionNoRepresentaANadie(representanteEntity))
        {
            throw new BusinessLogicException("El usuario ya representa un equipo");
        }
        if(!verificacionEquipoNoRepresentado(representanteEntity))
        {
            throw new BusinessLogicException("El equipo ya es representado");
        }
        if(representantePersistence.find(representanteEntity.getId())!=null)
        {
            throw new BusinessLogicException("El representante ya existe");
        }
        representantePersistence.create(representanteEntity);
        return representanteEntity;
    }        
    
    /**
     * busca un representante en la base de datos
     * @param representanteId el id del representante a buscar
     * @return el resultado de la busqueda
     */
    public RepresentanteEntity getRepresentante(Long representanteId)
    {
        LOGGER.log(Level.INFO, "se empieza la busqueda de representante con id={0}", representanteId);
        RepresentanteEntity res = representantePersistence.find(representanteId);
        if(res==null)
        {
            LOGGER.log(Level.INFO,"el representante no existe en la base de datos", representanteId);
        }
        LOGGER.log(Level.INFO,"Se termina el proceso de busqueda para el representante con id=", representanteId);
        return res;
    }
    /**
     * devuelve todos lo representantes en la base de datos
     * @return todos los representantes
     */
    public List<RepresentanteEntity> getRepresentantes()
    {
        LOGGER.log(Level.INFO,"Se va a hacer consulta de todos los representantes");
        List<RepresentanteEntity> res = representantePersistence.findAll();
        LOGGER.log(Level.INFO,"se termina la consulta de todos los representantes");
        return res;
    }
    
    /**
     * se elimina un representante
     * @param representanteId el id del representante a eliminar
     */
    public void deleteRepresentante(Long representanteId) 
    {
        LOGGER.log(Level.INFO, "Se empieza el proceso para eliminar el representante con id={0}", representanteId);
        representantePersistence.delete(representanteId);        
    }
    
    public RepresentanteEntity updateRepresentante(Long id,RepresentanteEntity representanteEntity ) throws BusinessLogicException
    {
        if(representanteEntity.representa==null)
        {
            throw new BusinessLogicException("El representante no tiene equipo");
        }
        if(representanteEntity.representante==null)
        {
            throw new BusinessLogicException("El representa no tiene usuario");
        }
        if(representantePersistence.find(id).getRepresenta().getId() != representanteEntity.getRepresenta().getId())
        {
            if(!verificacionEquipoNoRepresentado(representanteEntity))
            {
                throw new BusinessLogicException("Equipo ya representado");
            }
        }        
        return representanteEntity;
    }
    
    private boolean verificacionNoRepresentaANadie(RepresentanteEntity r)
    {
        System.out.println(representantePersistence.findAll().size());
        for(RepresentanteEntity rep : representantePersistence.findAll())
        {
            System.out.println(rep.getId()+"-----"+r.getId());
            if(rep.getRepresentante().getId()==r.getRepresentante().getId())
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean verificacionEquipoNoRepresentado(RepresentanteEntity r)
    {   
        for(RepresentanteEntity rep : representantePersistence.findAll())
        {
            if(rep.getRepresenta().getId()==r.getRepresenta().getId())
            {
                return false;
            }
        }
        return true;
    }
}
