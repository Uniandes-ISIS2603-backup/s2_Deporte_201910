/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Santiago Barbosa
 */
public class ReservaDTO implements Serializable{
    
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer duracionHoras;
     
    private String nombreUsuario;
    private Long idUsuario;
    private Long idReserva;
    private Long idCancha;
    
    private FranjaDTO franja;
    private CampeonatoDTO campeonato;
    private AmistosoDTO amistoso;
    private EntrenamientoDTO entrenamiento;
    
    public ReservaDTO () {
        
    }
    
    public ReservaDTO(ReservaEntity entidad){
        setId(entidad.getId());
        setFechaInicio(entidad.getFechaInicio());
        setFechaFin(entidad.getFechaFin());
        setAmistoso(new AmistosoDTO(entidad.getAmistoso()));
        setEntrenamiento(new EntrenamientoDTO(entidad.getEntrenamiento()));
        setFranja(new FranjaDTO(entidad.getFranja()));
    }
    
    public ReservaEntity toEntity(){
        ReservaEntity entidad=new ReservaEntity();
        entidad.setId(this.id);
        entidad.setFechaInicio(this.fechaInicio);
        entidad.setFechaFin(this.fechaFin);
        entidad.setAmistoso(this.amistoso.toEntity());
        entidad.setEntrenamiento(this.entrenamiento.toEntity());
        entidad.setFranja(this.franja.toEntity());
                
                
        return entidad;
    }
    /**
     * Returns fecha de inicio de la reserva
     * @return Date fecha de inicio d ela reserva
     */
    public Date getFechaInicio(){
        return fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
     * Returns duracion en horas de la reserva
     * @return Integer duracion en horas de la reserva
     */
    public Integer getDuracionHoras(){
        return duracionHoras;
    }
    
    /**
     * Establece la duracion en horas de la reserva
     * @param horas numero de horas que dura la reserva
     */
    public void setDuracionHoras(Integer horas){
        duracionHoras = horas;
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
    
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(Long idCancha) {
        this.idCancha = idCancha;
    }
    
}
