/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import javax.ws.rs.Path;

/**
 *
 * @author estudiante
 */
public class EntrenamientoDTO implements Serializable {
    /**
     * atributo que identifica de manera unica al entrenamiento
     */
    private Long id;

    
    /**
     * atributo que modela la relacion con equipo
     */
    private EquipoDTO equipo;
    /**
     * atributo que modela la relacion con reserva
     */
    private ReservaDTO reserva;
    
   
    public EntrenamientoDTO(){
        
    }
    
     public Long getId() {
        return id;
    }
  
    /**
     * metodo get para partido
     * @return partido
     */
    public EquipoDTO getEquipo(){
        return equipo;
    }
    /**
     * metodo get para reserva
     * @return reserva
     */
     public ReservaDTO getReserva(){
        return reserva;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
     /**
      * metodo set para equipo
      * @param equipo equipo asociado al entremianto
      */
     public void setEquipo(EquipoDTO equipo){
         this.equipo=equipo;
     }
     /**
      * metodo set para reserva
      * @param reserva reserva asociada al entrenamiento
      */
     public void setReserva(ReservaDTO reserva){
         this.reserva=reserva;
     }
}
