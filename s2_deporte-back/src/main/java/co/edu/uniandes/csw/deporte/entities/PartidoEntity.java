/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import co.edu.uniandes.csw.deporte.podam.DateStrategy;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author estudiante
 */
@Entity
public class PartidoEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToMany(mappedBy="partido")
    private List<EquipoEntity> equipos = new ArrayList<EquipoEntity>();
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    private List<Integer> puntaje = new ArrayList<Integer>();
}
