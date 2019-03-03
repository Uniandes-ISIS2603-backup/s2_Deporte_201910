/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import co.edu.uniandes.csw.deporte.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    @ManyToMany(mappedBy = "partidos")
    private List<EquipoEntity> participantes;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    private List<Integer> puntaje;

    public PartidoEntity() {
        this.puntaje = new ArrayList<>();
        this.participantes = new ArrayList<>();
    }
    /**
     * le asigna una fecha al partido
     * @param pFecha la fecha que se le asigna a un partido
     */
    public void setFecha(Date pFecha)
    {
        fecha=pFecha;
    }
    /**
     * devuelve la fecha del partido
     * @return fecha
     */
    public Date getFecha()
    {
        return fecha;
    }
    /**
     * le asigna participantes al partido
     * @param pEquipos los participantes que se asignan
     */
    public void setEquipos(List<EquipoEntity> pEquipos)
    {
        participantes=pEquipos;
    }
    /**
     * devuelve los participantes que van a participar
     * @return participantes
     */
    public List<EquipoEntity> getEquipos()
    {
        return participantes;
    }
    /**
     * modifica el puntaje de un partido
     * @param pPuntaje el puntaje nuevo
     */
    public void setPuntaje(List<Integer> pPuntaje)
    {
        puntaje=pPuntaje;
    }
    /**
     * devuelve el puntaje de un partido
     * @return puntaje
     */
    public List<Integer> getPuntaje()
    {
        return puntaje;
    }
}
