/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Santiago Barbosa
 */
@Entity
public class AgendaEntity extends BaseEntity implements Serializable{
    
    
    private Integer anio;
    private Integer mes;
    @PodamExclude
    @ManyToOne
    private CanchaEntity cancha;
    
    
    @PodamExclude
    @OneToMany
    private List<FranjaEntity> franjas;
    
    public AgendaEntity (){
        
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
     * @return the canchas
     */
    public CanchaEntity getCancha() {
        return cancha;
    }

    /**
     * @param canchas the canchas to set
     */
    public void setCancha(CanchaEntity cancha) {
        this.cancha = cancha;
    }

    /**
     * @return the franjas
     */
    public List<FranjaEntity> getFranjas() {
        return franjas;
    }

    /**
     * @param franjas the franjas to set
     */
    public void setFranjas(List<FranjaEntity> franjas) {
        this.franjas = franjas;
    }
}
