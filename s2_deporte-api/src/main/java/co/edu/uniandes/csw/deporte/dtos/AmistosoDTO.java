/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

/**
 *
 * @author Nicolas De la Hoz
 */
public class AmistosoDTO {
    /**
     * atributo que modela la relacion con partido
     */
    private PartidoDTO partido;
    /**
     * atributo que modela la relacion con reserva
     */
    private ReservaDTO reserva;
    
    public AmistosoDTO(){
        
    }
    /**
     * metodo get para partido
     * @return partido
     */
    public PartidoDTO getPartido(){
        return partido;
    }
    /**
     * metodo get para reserva
     * @return reserva
     */
     public ReservaDTO getReserva(){
        return reserva;
    }
     public void setPartido(PartidoDTO partido){
         this.partido=partido;
 
     }
     
     public void setReserva(ReservaDTO reserva){
         this.reserva=reserva;
     }
}
