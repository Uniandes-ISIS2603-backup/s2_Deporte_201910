/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CanchaEntity extends BaseEntity implements Serializable{
    
    public String zona;
    
    public String ciudad;
    
    public String direccion;
    
    public String caracterizticas;
    
    public List<Integer> contacto;
    
    public boolean reservada;
    
    public String tipo;
    @PodamExclude
    @ManyToOne
    public PropietarioEntity propietario;
    @PodamExclude
    @OneToMany
    public List<AgendaEntity> agendas;
    @PodamExclude
    @OneToMany
    public List<ReservaEntity> reservas;
    
    public CanchaEntity (){
        
    }
    
    /**
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the caracterizticas
     */
    public String getCaracterizticas() {
        return caracterizticas;
    }

    /**
     * @param caracterizticas the caracterizticas to set
     */
    public void setCaracterizticas(String caracterizticas) {
        this.caracterizticas = caracterizticas;
    }

    /**
     * @return the contacto
     */
    public List<Integer> getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(List<Integer> contacto) {
        this.contacto = contacto;
    }

    /**
     * @return the reservada
     */
    public boolean isReservada() {
        return reservada;
    }

    /**
     * @param reservada the reservada to set
     */
    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the propietario
     */
    public PropietarioEntity getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(PropietarioEntity propietario) {
        this.propietario = propietario;
    }

    /**
     * @return the agendas
     */
    public List<AgendaEntity> getAgendas() {
        return agendas;
    }

    /**
     * @param agendas the agendas to set
     */
    public void setAgendas(List<AgendaEntity> agendas) {
        this.agendas = agendas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
}
