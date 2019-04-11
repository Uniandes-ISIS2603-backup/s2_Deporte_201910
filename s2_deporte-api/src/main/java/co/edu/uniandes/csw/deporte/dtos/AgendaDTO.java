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
 * @author estudiante
 */
public class AgendaDTO implements Serializable{
    
    public Integer anio;
    
    public Integer mes;
    
    public CanchaDTO cancha;
    
    private Long id;
    
    public AgendaDTO () {
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param agendaEntity: Es la entidad que se va a convertir a DTO
     */
    public AgendaDTO(AgendaEntity agendaEntity) {
        if (agendaEntity != null) {
            this.anio = agendaEntity.getAnio();
            this.mes = agendaEntity.getMes();
            this.cancha = (new CanchaDTO(agendaEntity.getCancha()));
            this.id = agendaEntity.getId();
        }
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
    
        /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public AgendaEntity toEntity() {
        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setAnio(this.anio);
        agendaEntity.setMes(this.mes);
        return agendaEntity;
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
}