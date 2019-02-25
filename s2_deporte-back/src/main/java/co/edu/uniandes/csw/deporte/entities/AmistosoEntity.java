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
public class AmistosoEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @OneToOne(
            mappedBy= "amistoso"
    )
    ReservaEntity reserva;

    @PodamExclude
    @OneToOne
    PartidoEntity partido;
    
     public PartidoEntity getPartido() {
        return partido;
    }

    public void setPartido(PartidoEntity partido) {
        this.partido = partido;
    }
   
    
    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
}
