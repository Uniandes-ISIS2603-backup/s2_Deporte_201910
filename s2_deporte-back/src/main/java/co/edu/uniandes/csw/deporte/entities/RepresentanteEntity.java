/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class RepresentanteEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToOne(cascade=CascadeType.PERSIST)
    public EquipoEntity representa;
    
    @PodamExclude 
    @OneToOne(cascade=CascadeType.PERSIST)
    public ClienteEntity representante;
    
    public RepresentanteEntity()
    {
        
    }

    public EquipoEntity getRepresenta() {
        return representa;
    }

    public void setRepresenta(EquipoEntity representa) {
        this.representa = representa;
    }

    public ClienteEntity getRepresentante() {
        return representante;
    }

    public void setRepresentante(ClienteEntity representante) {
        this.representante = representante;
    }
    
}
