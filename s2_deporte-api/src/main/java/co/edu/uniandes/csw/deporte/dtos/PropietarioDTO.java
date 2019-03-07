/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import java.io.Serializable;

/**
 *
 * @author Santiago Serrano
 */
public class PropietarioDTO implements Serializable{
    
    public Long id;
    
    public Integer numCanchas;
    
    public PropietarioDTO () {
        
    }
    
        
        /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param propietarioDTO: Es la entidad que se va a convertir a DTO
     */
    public PropietarioDTO(PropietarioEntity propietarioEntity) {
        if (propietarioEntity != null) {
            this.id = propietarioEntity.getId();
            this.numCanchas = propietarioEntity.getNumCanchas();
        }
    }

    /**
     * @return the numCanchas
     */
    public Integer getNumCanchas() {
        return numCanchas;
    }

    /**
     * @param numCanchas the numCanchas to set
     */
    public void setNumCanchas(Integer numCanchas) {
        this.numCanchas = numCanchas;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
                /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PropietarioEntity toEntity() {
        PropietarioEntity propietarioEntity = new PropietarioEntity();
        propietarioEntity.setNumCanchas(this.numCanchas);
        propietarioEntity.setId(this.id);
        return propietarioEntity;
    }
}
