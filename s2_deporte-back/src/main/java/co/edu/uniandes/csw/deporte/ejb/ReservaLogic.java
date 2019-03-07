/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.entities.ReservaEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.deporte.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas De la Hoz
 */
@Stateless
public class ReservaLogic {
    
    public static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());
    @Inject
    public ReservaPersistence persistence;
    
    public ReservaEntity createReserva(ReservaEntity reserva) throws BusinessLogicException{
        LOGGER.log(Level.INFO,"Empieza el proceso de crear un reserva");
        if(!revisarFecha(reserva)){
            throw new BusinessLogicException("La fecha de inicio de la reserva debe ser inferior a la fecha final");
        }
        reserva=persistence.create(reserva);
        LOGGER.log(Level.INFO,"Termina el proceso de crear un reserva");
        return reserva;
    }



    public ReservaEntity find(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO,"Empieza el proceso de buscar un reserva");
        ReservaEntity reserva=persistence.find(id); 
        if(reserva==null){
            throw new BusinessLogicException("No se pudo encontrar el reserva");
        }
        LOGGER.log(Level.INFO,"Termina el proceso de buscar un reserva");
        return reserva;
    }

    public List<ReservaEntity> findAll() {
        LOGGER.log(Level.INFO,"Empieza el proceso de buscar todos los reservas");
        List<ReservaEntity> reservas=persistence.findAll();
        LOGGER.log(Level.INFO,"Termina el proceso de buscar todos los reservas");
        return reservas;
        
    }

    public void update(ReservaEntity reserva) throws BusinessLogicException {
        LOGGER.log(Level.INFO,"Empieza el proceso de actualizar un amistoso");
        if(!revisarFecha(reserva)){
            throw new BusinessLogicException("La fecha de inicio de la reserva debe ser inferior a la fecha final");
        }
        persistence.update(reserva);
        LOGGER.log(Level.INFO,"Termina el proceso de actualizar un amistoso");
        
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO,"Empieza el proceso de eliminar un amistoso");
        persistence.delete(id);
        LOGGER.log(Level.INFO,"Termina el proceso de eliminar un amistoso");
    }
    
    /**
     * Metodo que retorna si la fecha inicio es menor a la fecha fin de una reserva
     * @param reserva reserva a la cual se revisara sus fechas
     * @return true si fecha inicio menor a fecha fin, false en caso contrario
     */
    public boolean revisarFecha(ReservaEntity reserva){
        
        Date in=reserva.getFechaInicio();
        Date fin=reserva.getFechaFin();
        boolean consistente=false;
        if(in.before(fin)){
            consistente=true;
        }
        return consistente;
    }
    
}
