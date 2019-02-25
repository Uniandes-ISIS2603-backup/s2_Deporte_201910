/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nicolas De la Hoz
 */
@Entity
public class EntrenamientoEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @OneToOne
    EquipoEntity equipo;
    
    public EquipoEntity getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntity equipo) {
        this.equipo = equipo;
    }
    
    @PodamExclude
    @OneToOne(
            mappedBy= "entrenamiento"
    )
    ReservaEntity reserva;
    
     public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
}
