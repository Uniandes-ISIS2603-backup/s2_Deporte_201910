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

/**
 *
 * @author estudiante
 */
@Entity
public class AgendaEntity extends BaseEntity implements Serializable{
    
    private Integer anio;
    private Integer mes;
    @ManyToMany(mappedBy = "agendas")
    private List<CanchaEntity> canchas;
    //private List<FranjaEntity> franjas;
    
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
    public List<CanchaEntity> getCanchas() {
        return canchas;
    }

    /**
     * @param canchas the canchas to set
     */
    public void setCanchas(List<CanchaEntity> canchas) {
        this.canchas = canchas;
    }
}
