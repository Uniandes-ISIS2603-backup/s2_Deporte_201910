/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Nicolas Poch
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<EquipoEntity> equipos = new ArrayList<EquipoEntity>();
    
    //@PodamExclude
    //@OneToMany(mappedBy = "cliente")
    //private List<PostEntity> posts = new ArrayList<PostEntity>();
    /**
     * devuelve la la lista de equipos
     * @return equipos
     */
    public List<EquipoEntity> getEquipos()
    {
        return equipos;
    }
    /**
     * modifica la lista de equipos
     * @param pEquipos los nuevos equipos
     */
    public void setEquipos(List<EquipoEntity> pEquipos)
    {
        equipos=pEquipos;
    }
    
    /**
     * Hacer los de post
     */
}

