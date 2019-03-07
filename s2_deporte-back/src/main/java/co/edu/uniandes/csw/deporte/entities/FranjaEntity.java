/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Santiago Barbosa
 */
@Entity
public class FranjaEntity extends BaseEntity implements Serializable{
    
    @Temporal(TemporalType.TIME)
    public Date fechaInicio;
    
    @Temporal(TemporalType.TIME)
    public Date fechaFin;
    
    public Integer duracionHoras;
    
    public Boolean ocupada;
    
    public Long idReserva;
    
    @PodamExclude
    @ManyToOne
    public AgendaEntity agenda;
    
    @PodamExclude
    @OneToOne
    public ReservaEntity reserva;
    
    
    public FranjaEntity(){
        
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the duracionHoras
     */
    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    /**
     * @param duracionHoras the duracionHoras to set
     */
    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    /**
     * @return the ocupada
     */
    public Boolean getOcupada() {
        return ocupada;
    }

    /**
     * @param ocupada the ocupada to set
     */
    public void setOcupada(Boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * @return the idReserva
     */
    public Long getIdReserva() {
        return idReserva;
    }

    /**
     * @param idReserva the idReserva to set
     */
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return the agenda
     */
    public AgendaEntity getAgenda() {
        return agenda;
    }

    /**
     * @param agenda the agenda to set
     */
    public void setAgenda(AgendaEntity agenda) {
        this.agenda = agenda;
    }

    /**
     * @return the reserva
     */
    public ReservaEntity getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
    
    
    
    
    
    
}
