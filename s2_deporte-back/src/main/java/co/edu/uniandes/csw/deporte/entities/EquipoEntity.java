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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable
{
        
    @PodamExclude
    @ManyToMany(mappedBy="equipos")
    private List<PartidoEntity> partidos = new ArrayList<PartidoEntity>();
    
    @PodamExclude
    @ManyToMany
    private List<ClienteEntity> jugadores = new ArrayList<ClienteEntity>();
    private ClienteEntity representante = new ClienteEntity();
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
     * Asigna un representante para un equipo
     * @param pR el ClienteEntity que va a representar al equipo
     */
    public void setRepresentante(ClienteEntity pR)
    {
        representante=pR;
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
     * devuelve los partidos del equipo
     * @return partidos
     */
    public List<PartidoEntity> getPartidos()
    {
        return partidos;
    }
    /**
     * modifica los partidos de un equipo
     * @param pPartidos los partidos que se insertan
     */
    public void setPartidos(List<PartidoEntity> pPartidos)
    {
        partidos=pPartidos;
    }
}
