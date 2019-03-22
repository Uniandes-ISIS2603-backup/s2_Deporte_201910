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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
    @ManyToMany
    public List<PartidoEntity> partidos;
    
    @PodamExclude
    @ManyToMany(mappedBy ="equipos")
    public List<ClienteEntity> jugadores;
   
    @PodamExclude
    @OneToOne(mappedBy = "representa")
    public RepresentanteEntity representante;
    
    public String nombre;

    public EquipoEntity() {
        this.jugadores = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public RepresentanteEntity getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteEntity representante) {
        this.representante = representante;
    }   
    
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
      public String getNombre() {
          return nombre;
      }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
