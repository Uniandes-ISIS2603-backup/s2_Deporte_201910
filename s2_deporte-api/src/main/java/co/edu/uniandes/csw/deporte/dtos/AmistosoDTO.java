/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.AmistosoEntity;
import java.io.Serializable;

/**
 *
 * @author Nicolas De la Hoz
 */

public class AmistosoDTO implements Serializable{
    /**
     * atributo que identifica unicamente el amistoso
     */
    public Long id;
    /**
     * atributo que modela la relacion con partido
     */
    public PartidoDTO partido;
    /**
     * atributo que modela la relacion con reserva
     */
    public ReservaDTO reserva;
    
    public AmistosoDTO(){
        
    }
    
    public AmistosoDTO(AmistosoEntity entidad){
        if(entidad!=null){
            setId(entidad.getId());
            setPartido(new PartidoDTO(entidad.getPartido()));
            setReserva(new ReservaDTO(entidad.getReserva()));
        }
    }
    
    public AmistosoEntity toEntity(){
        AmistosoEntity entidad=new AmistosoEntity();
        entidad.setId(this.getId());
        entidad.setPartido(this.partido.toEntity());
        entidad.setReserva(this.reserva.toEntity());
        return entidad;
    }
    /**
     * metodo get para id
     * @return id
     */
    public Long getId(){
        return id;
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
     /**
      * metodo set para id
      * @param id id del amistoso
      */
     public void setId(Long id){
         this.id=id;
     }
     /**
      * metodo set para partido
      * @param partido partido asociado al amistoso
      */
     public void setPartido(PartidoDTO partido){
         this.partido=partido;
 
     }
     /**
      * Metodo set para reserva
      * @param reserva reserva asociada al amistoso
      */
     public void setReserva(ReservaDTO reserva){
         this.reserva=reserva;
     }
}
