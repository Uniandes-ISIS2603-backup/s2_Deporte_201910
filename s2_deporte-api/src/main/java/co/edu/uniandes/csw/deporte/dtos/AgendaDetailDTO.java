/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AgendaDetailDTO extends AgendaDTO implements Serializable {
    
    private List<FranjaDTO> franjas;
    
    public AgendaDetailDTO (){
        
    }
}
