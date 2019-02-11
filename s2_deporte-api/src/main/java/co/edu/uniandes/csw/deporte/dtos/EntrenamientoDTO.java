/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

/**
 *
 * @author estudiante
 */
public class EntrenamientoDTO {
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
