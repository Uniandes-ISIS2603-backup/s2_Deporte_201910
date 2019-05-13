/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;
import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author estudiante
 */
public class EquipoDetailDTO extends EquipoDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * los jugadores pertenecientes a un equipo
     */
    private List<ClienteDTO> jugadores;
    /**
     * los partidos que tiene el equipo
     */
    private List<PartidoDTO> partidos;
    //Constructor---------------------------------------------------------------
    public EquipoDetailDTO()
    {
        super();
    }
    public EquipoDetailDTO(EquipoEntity entity) {
        super(entity);
        if (entity != null) {
            jugadores = new ArrayList<>();
            for (ClienteEntity entityClientes : entity.getJugadores()) {
                jugadores.add(new ClienteDTO(entityClientes));
            }
        }
    }
    @Override
    public EquipoEntity toEntity() {
        EquipoEntity entity = super.toEntity();
        if (jugadores != null) {
            List<ClienteEntity> clientesEntity = new ArrayList<>();
            for (ClienteDTO dtoCliente : jugadores) {
                clientesEntity.add(dtoCliente.toEntity());
            }
            entity.setJugadores(clientesEntity);
            
            List<PartidoEntity> partidosEntity = new ArrayList<>();
            for (PartidoDTO dtoPartido : partidos) {
                partidosEntity.add(dtoPartido.toEntity());
            }
            entity.setJugadores(clientesEntity);
        }

        return entity;
    }
    //Métodos-------------------------------------------------------------------

    public List<ClienteDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<ClienteDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public List<PartidoDTO> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<PartidoDTO> partidos) {
        this.partidos = partidos;
    }
    
}
