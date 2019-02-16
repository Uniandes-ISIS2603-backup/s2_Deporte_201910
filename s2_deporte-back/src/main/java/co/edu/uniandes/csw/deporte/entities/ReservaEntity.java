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



/**
 *
 * @author Nicolas De la Hoz
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable {

    @Id
    private Long id;
    
    private Date fechaInicio;

    private Date fechaFin;
    
    @OneToOne(
            mappedBy = "reserva",
            orphanRemoval=true
    )
    AmistosoEntity amistoso;
    
    @OneToOne(
            mappedBy = "reserva",
            orphanRemoval=true
    )
    EntrenamientoEntity entrenamiento;

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
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
