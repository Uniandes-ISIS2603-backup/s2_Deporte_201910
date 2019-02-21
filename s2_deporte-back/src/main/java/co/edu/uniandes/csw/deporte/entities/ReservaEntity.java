/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;



/**
 *
 * @author Nicolas De la Hoz
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    
    @OneToOne
    AmistosoEntity amistoso;
    
    @OneToOne
    EntrenamientoEntity entrenamiento;

    @OneToOne
    FranjaEntity franja;
    
    public AmistosoEntity getAmistoso() {
        return amistoso;
    }

    public void setAmistoso(AmistosoEntity amistoso) {
        this.amistoso = amistoso;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
}
