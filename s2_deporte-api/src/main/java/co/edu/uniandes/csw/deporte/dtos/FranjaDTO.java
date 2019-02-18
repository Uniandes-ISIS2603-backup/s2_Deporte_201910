/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Santiago Barbosa
 */
public class FranjaDTO implements Serializable{
    
    private Date fechaInicio;
    private Date fechaFin;
    private int duracionTotalHoras;
    private boolean ocupada;
    private int idReserva;
    private AgendaDTO agenda;
    
    
    public FranjaDTO(){
        
    }
    
    
    /**
     * Retorna la fecha de inicio de la franja
     * @return Date: fecha de inicio de la franja
     */
    public Date getFechaInicio(){
        return fechaInicio;
    }
    
    /**
     * Establece la fecha de inicio de la franja
     * @param fecha Date de inicio de la franja
     */
    public void setFechaInicio(Date fecha){
        fechaInicio = fecha;
    }
    
    /**
     * Retorna la fecha de fin de la franja
     * @return Date: fecha de fin de la franja
     */
    public Date getFechaFin(){
        return fechaFin;
    }
    
    /**
     * Establece la fecha de fin de la franja
     * @param fecha Date de fin de la franja
     */
    public void setFechaFin(Date fecha){
        fechaFin = fecha;
    }
    
    /**
     * Retorna la duracion de horas de la franja
     * @return int: duracion en horas de la franja
     */
    public int getDuracionTotalHoras(){
        return duracionTotalHoras;
    }
    
    /**
     * Establece la duercion en horas de la franja
     * @param horas
     */
    public void setDuracionTotalHoras(int horas){
        duracionTotalHoras = horas;
    }
    
    /**
     * Verifica si la franja esta ocupada (reservada)
     * @return boolean: Verdadero si la franja esta reservada. Falso si no.
     */
    public boolean getOcupada(){
        return ocupada;
    }
    
    /**
     * Establece si la franje esta ocupada o no
     * @param estaOcupada verdadero si esta ocupada. Falso si no
     */
    public void setOcupada(boolean estaOcupada){
        ocupada = estaOcupada;
    }
    
    /**
     * Retorna el id de la reserva en caso de estar ocupada. -1 si no lo está
     * @return int: id de la reserva si está ocupada. -1 si no lo está.
     */
    public int getIdReserva(){
        if(getOcupada())
        {
            return idReserva;
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Establece el id de la reseva que ocupa esta franja
     * @param id de la reserva que ocupa esta franja
     */
    public void setIdReserva(int id){
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
}
