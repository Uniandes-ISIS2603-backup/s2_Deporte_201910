/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class CanchaDTO implements Serializable{
    
    private String id;
    private String zona;
    private String direccion;
    private String ciudad;
    private ArrayList<Integer> contacto;
    private String caracterizticas;
    private boolean reservada;
    private String tipoCancha;
    
    public CanchaDTO(){
        
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
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
    public ArrayList<Integer> getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(ArrayList<Integer> contacto) {
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
    
}
