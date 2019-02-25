/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import java.io.Serializable;

/**
 *
 * @author Santiago Barbosa
 */
public class AgendaDTO implements Serializable{
    
    private Integer anio;
    
    private Integer mes;
    
    private CanchaDTO cancha;
    
    public AgendaDTO () {
        
    }

    public AgendaEntity toEntity(){
        AgendaEntity entity = new AgendaEntity();
        entity.setAnio(this.getAnio());
        entity.setMes(this.getMes());
        return entity;
        
    }
    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * @return the cancha
     */
    public CanchaDTO getCancha() {
        return cancha;
    }

    /**
     * @param cancha the cancha to set
     */
    public void setCancha(CanchaDTO cancha) {
        this.cancha = cancha;
    }
    
}
