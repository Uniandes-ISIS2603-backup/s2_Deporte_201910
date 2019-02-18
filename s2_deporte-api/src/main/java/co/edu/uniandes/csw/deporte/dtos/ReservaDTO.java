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
public class ReservaDTO implements Serializable{
    
    private Date fechaInicio;
    private Date fechaFin;
    private String nombreUsuario;
    private int idUsuario;
    private int duracionHoras;
    private int idReserva;
    private int idCancha;
    private FranjaDTO franja;
    private CampeonatoDTO campeonato;
    private AmistosoDTO amistoso;
    private EntrenamientoDTO entrenamiento;
    
    public ReservaDTO () {
        
    }
    
    /**
     * Returns fecha de inicio de la reserva
     * @return Date fecha de inicio d ela reserva
     */
    public Date getFechaInicio(){
        return fechaInicio;
    }
    
    /**
     * Establece la fecha de inicio de la reserva
     * @param fechaInicioSet Date de inicio de la reserva
     */
    public void setFechaInicio(Date fechaInicioSet){
        fechaInicio = fechaInicioSet;
    }
    
    /**
     * Returns fecha de fin de la reserva
     * @return Date fecha fin de la reserva
     */
    public Date getFechaFin(){
        return fechaFin;
    }
    
    /**
     * Establece la fecha de fin de la reserva
     * @param fechaFinSet Date de fin de la reserva
     */
    public void setFechaFin(Date fechaFinSet){
        fechaFin = fechaFinSet;
    }
    
    
    /**
     * Returns nombre del usuario dueño de la reserva
     * @return String nombre del usuario dueño de la reserva
     */
    public String getNombreUsuario(){
        return nombreUsuario;
    }
    
    /**
     * Establece el nombre del usuario dueño de la reserva
     * @param nombre String que contiene el nombre del usuario
     */
    public void setNombreUsuario(String nombre){
        nombreUsuario = nombre;
    }
    
    
    /**
     * Returns id del usuario dueño de la reserva
     * @return int id del usuario dueño de la reserva
     */
    public int getIdUsuario(){
        return idUsuario;
    }
    
    /**
     * Establece el id del usuario dueño de la reserva
     * @param id del usuario
     */
    public void setIdUsuario(int id){
        idUsuario = id;
    }
    
    
    /**
     * Returns duracion en horas de la reserva
     * @return int duracion en horas de la reserva
     */
    public int getDuracionHoras(){
        return duracionHoras;
    }
    
    /**
     * Establece la duracion en horas de la reserva
     * @param horas numero de horas que dura la reserva
     */
    public void setDuracionHoras(int horas){
        duracionHoras = horas;
    }
    
    
    /**
     * Returns id de la reserva
     * @return int id de la reserva
     */
    public int getIdReserva(){
        return idReserva;
    }
    
    /**
     * Establece el id de la reserva
     * @param id de la reserva
     */
    public void setIdReserva(int id){
        idReserva = id;
    }
    
    
    /**
     * Returns id de la cancha reservada
     * @return int id de la cancha reservada
     */
    public int getIdCancha(){
        return idCancha;
    }
    
    /**
     * Establece el id de la cancha reservada
     * @param id de la cancha reservada
     */
    public void setIdCancha(int id){
        idCancha = id;
    }
    
    /**
     * Retorna la franja que ocupa la reserva
     * @return FranjaDTO una lista de las franjas que ocupa la reserva
     */
    public FranjaDTO getFranja(){
        return franja;
    }
    
    /**
     * Establece la franja que ocupa la reserva
     * @param franjaN franja que ocupara la reserva
     */
    public void setFranja(FranjaDTO franjaN){
        franja = franjaN;
    }
    
    
    
    /**
     * Retorna el campeonato al cual hace parte esta frnaja. null si no tiene campeonato
     * @return campeonato
     */
    public CampeonatoDTO getCampeonato(){
        return campeonato;
    }
    
    /**
     * Establece el campeonato al cual hace parte esta franja
     * @param camp
     */
    public void setCampeonato(CampeonatoDTO camp){
        campeonato = camp;
    }
    
    /**
     * Retorna el amistoso al cual hace parte esta frnaja. null si no tiene amistoso
     * @return amistoso
     */
    public AmistosoDTO getAmistoso(){
        return amistoso;
    }
    
    /**
     * Establece el amistoso al cual hace parte esta franja
     * @param amis
     */
    public void setAmistoso(AmistosoDTO amis){
        amistoso = amis;
    }
    
    /**
     * Retorna el entrenamiento al cual hace parte esta frnaja. null si no tiene entrenamiento
     * @return entrenamiento
     */
    public EntrenamientoDTO getEntrenamiento(){
        return entrenamiento;
    }
    
    /**
     * Establece el entrenamiento al cual hace parte esta franja
     * @param entre
     */
    public void setEntrenamiento(EntrenamientoDTO entre){
        entrenamiento = entre;
    }
    
}
