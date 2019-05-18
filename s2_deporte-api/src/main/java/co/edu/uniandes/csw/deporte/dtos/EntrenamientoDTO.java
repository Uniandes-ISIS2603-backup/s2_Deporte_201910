/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.EntrenamientoEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class EntrenamientoDTO implements Serializable {
    /**
     * atributo que identifica de manera unica al entrenamiento
     */
    public Long id;

    
    /**
     * atributo que modela la relacion con equipo
     */
    public EquipoDTO equipo;
    /**
     * atributo que modela la relacion con reserva
     */
    public ReservaDTO reserva;
    
   
    public EntrenamientoDTO(){
        
    }
    
    public EntrenamientoDTO(EntrenamientoEntity entidad){
        if(entidad!=null){
            this.id=entidad.getId();
            this.equipo=new EquipoDTO(entidad.getEquipo());
            this.reserva=new ReservaDTO(entidad.getReserva());
        }
    }
    
    public EntrenamientoEntity toEntity(){
        EntrenamientoEntity entidad=new EntrenamientoEntity();
        entidad.setId(this.id);
        entidad.setEquipo(this.equipo.toEntity());
        entidad.setReserva(this.reserva.toEntity());
        return entidad;
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
