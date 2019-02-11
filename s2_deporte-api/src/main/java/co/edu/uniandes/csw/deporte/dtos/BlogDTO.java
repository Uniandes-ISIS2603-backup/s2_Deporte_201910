/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class BlogDTO implements Serializable{
    
      private CampeonatoDTO campeonatoDTO;
    public BlogDTO()
    {
        
    }

    /**
     * @return the campeonatoDTO
     */
    public CampeonatoDTO getCampeonatoDTO() {
        return campeonatoDTO;
    }

    /**
     * @param campeonatoDTO the campeonatoDTO to set
     */
    public void setCampeonatoDTO(CampeonatoDTO campeonatoDTO) {
        this.campeonatoDTO = campeonatoDTO;
    }
    
}
