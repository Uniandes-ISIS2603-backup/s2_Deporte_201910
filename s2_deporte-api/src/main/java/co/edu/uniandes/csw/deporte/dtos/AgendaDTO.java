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
public class AgendaDTO implements Serializable{
    
    private int anio;
    
    private int mes;
    
    private CanchaDTO cancha;
    
    public AgendaDTO () {
        
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
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
