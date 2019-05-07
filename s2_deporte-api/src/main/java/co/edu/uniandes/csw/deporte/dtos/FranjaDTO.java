/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Santiago Barbosa
 */
public class FranjaDTO implements Serializable{
    
    private Integer horaInicio;
    private Integer horaFin;
    private Integer dia;
    private Long id;
    private Integer duracionTotalHoras;
    private Boolean ocupada;
    private Long idReserva;
    private AgendaDTO agenda;
    
    
    public FranjaDTO(){
        
    }
    
    public FranjaDTO(FranjaEntity entity){
        if(entity != null)
        {
            this.duracionTotalHoras = entity.getDuracionHoras();
            this.horaFin = entity.getHoraFin();
            this.id = entity.getId();
            this.dia = entity.getDia();
            this.horaInicio = entity.getHoraInicio();
            this.idReserva = entity.getIdReserva();
            this.ocupada = entity.getOcupada();
            this.agenda = new AgendaDTO(entity.getAgenda());
        }
    }
    
    public FranjaEntity toEntity(){
        FranjaEntity entity = new FranjaEntity();
        entity.setDuracionHoras(this.getDuracionTotalHoras());
        entity.setHoraFin(this.getHoraFin());
        entity.setHoraInicio(this.getHoraInicio());
        entity.setId(this.id);
        entity.setIdReserva(this.getIdReserva());
        entity.setOcupada(this.getOcupada());
        entity.setAgenda(this.agenda.toEntity());
        entity.setDia(this.getDia());
        return entity;
    }
    
    /**
     * Retorna la fecha de inicio de la franja
     * @return Date: fecha de inicio de la franja
     */
    public Integer getHoraInicio(){
        return horaInicio;
    }
    
    /**
     * Establece la fecha de inicio de la franja
     * @param fecha Date de inicio de la franja
     */
    public void setHoraInicio(Integer fecha){
        horaInicio = fecha;
    }
    
    /**
     * Retorna la fecha de fin de la franja
     * @return Date: fecha de fin de la franja
     */
    public Integer getHoraFin(){
        return horaFin;
    }
    
    /**
     * Establece la fecha de fin de la franja
     * @param fecha Date de fin de la franja
     */
    public void setHoraFin(Integer fecha){
        horaFin = fecha;
    }
    
    /**
     * Retorna la duracion de horas de la franja
     * @return int: duracion en horas de la franja
     */
    public Integer getDuracionTotalHoras(){
        return duracionTotalHoras;
    }
    
    /**
     * Establece la duercion en horas de la franja
     * @param horas
     */
    public void setDuracionTotalHoras(Integer horas){
        duracionTotalHoras = horas;
    }
    
    /**
     * Verifica si la franja esta ocupada (reservada)
     * @return boolean: Verdadero si la franja esta reservada. Falso si no.
     */
    public Boolean getOcupada(){
        return ocupada;
    }
    
    /**
     * Establece si la franje esta ocupada o no
     * @param estaOcupada verdadero si esta ocupada. Falso si no
     */
    public void setOcupada(Boolean estaOcupada){
        ocupada = estaOcupada;
    }
    
    /**
     * Retorna el id de la reserva en caso de estar ocupada. -1 si no lo está
     * @return int: id de la reserva si está ocupada. -1 si no lo está.
     */
    public Long getIdReserva(){
        return idReserva;
    }
    
    /**
     * Establece el id de la reseva que ocupa esta franja
     * @param id de la reserva que ocupa esta franja
     */
    public void setIdReserva(Long id){
        idReserva = id;
    }
    
    /**
     * Retorna la agenda que contiene esta franja
     * @return AgendaDTO 
     */
    public AgendaDTO getAgenda(){
        return agenda;
    }
    
    /**
     * Establece la agenda que contiene esta franja
     * @param agendaPadre agenda que contiene esta franja
     */
    public void setAgenda(AgendaDTO agendaPadre){
        agenda = agendaPadre;
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
     * @return the dia
     */
    public Integer getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(Integer dia) {
        this.dia = dia;
    }
}
