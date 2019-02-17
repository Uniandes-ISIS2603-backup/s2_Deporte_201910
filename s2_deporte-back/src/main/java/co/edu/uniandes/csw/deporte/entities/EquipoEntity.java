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
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
public class EquipoEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToOne(mappedBy = "equipo")
    private ClienteEntity representante = new ClienteEntity();
    
    @PodamExclude
    @OneToMany(mappedBy="equipo")
    private List<ClienteEntity> jugadores = new ArrayList<ClienteEntity>();
    /**
     * devuelve la lista de jugadores en el equipo
     * @return jugadores
     */
    public List<ClienteEntity> getJugadores()
    {
        return jugadores;
    }
    /**
     * modifica la lista de jugadores
     * @param pJugadores los nuevos jugadores
     */
    public void setJugadores(List<ClienteEntity> pJugadores)
    {
        jugadores=pJugadores;
    }
    /**
     * devuelve el representante del equipo
     * @return representante
     */
    public ClienteEntity getRepresentante()
    {
        return representante;
    }
    /**
     * modifica el representante del equipo
     * @param pRepresentante el nuevo rerepsentante del equipo
     */
    public void setRepresentante(ClienteEntity pRepresentante)
    {
        representante=pRepresentante;
    }
}
