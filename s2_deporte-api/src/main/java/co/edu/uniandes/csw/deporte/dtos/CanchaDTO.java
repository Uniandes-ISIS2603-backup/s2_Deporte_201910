/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class CanchaDTO implements Serializable{
    
    public Long id;
    
    public String zona;
    
    public String direccion;
    
    public String ciudad;
    
    public List<Integer> contacto;
    
    public String caracterizticas;
    
    public Boolean reservada;
    
    public String tipoCancha;
    
    public PropietarioDTO propietario;
    
    public CanchaDTO(){
        
    }
    
        /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param canchaDTO: Es la entidad que se va a convertir a DTO
     */
    public CanchaDTO(CanchaEntity canchaEntity) {
        if (canchaEntity != null) {
            this.id = canchaEntity.getId();
            this.caracterizticas = canchaEntity.getCaracterizticas();
            this.ciudad = canchaEntity.getCiudad();
            this.direccion = canchaEntity.getDireccion();
            this.tipoCancha = canchaEntity.getTipo();
            this.zona = canchaEntity.getZona();
            this.contacto = canchaEntity.getContacto();
            this.reservada = canchaEntity.isReservada();
        //    this.propietario = canchaEntity.getPropietario();
        }
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
     * @return the reservada
     */
    public Boolean getReservada() {
        return reservada;
    }

    /**
     * @param reservada the reservada to set
     */
    public void setReservada(Boolean reservada) {
        this.reservada = reservada;
    }

    /**
     * @return the tipoCancha
     */
    public String getTipoCancha() {
        return tipoCancha;
    }

    /**
     * @param tipoCancha the tipoCancha to set
     */
    public void setTipoCancha(String tipoCancha) {
        this.tipoCancha = tipoCancha;
    }

    /**
     * @return the propietario
     */
    public PropietarioDTO getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(PropietarioDTO propietario) {
        this.propietario = propietario;
    }
    
            /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public CanchaEntity toEntity() {
        CanchaEntity canchaEntity = new CanchaEntity();
        canchaEntity.setCaracterizticas(this.caracterizticas);
        canchaEntity.setCiudad(this.ciudad);
        canchaEntity.setContacto(this.contacto);
        canchaEntity.setDireccion(this.direccion);
    //    canchaEntity.setPropietario(this.propietario);
        canchaEntity.setTipo(this.tipoCancha);
        canchaEntity.setZona(this.zona);
        canchaEntity.setReservada(this.reservada);
        canchaEntity.setId(this.id);
        return canchaEntity;
    }
}
