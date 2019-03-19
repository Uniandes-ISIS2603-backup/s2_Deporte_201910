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
    public ArrayList<ClienteDTO> jugadores;
    
    public ArrayList<PartidoDTO> partidos;
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
    /**
     * agrega un jugador a la lista de jugadores
     * @param pJugador el jugador a agregar, pJugador!=null && pJugador!cjugadores
     */
    public void addJugador(ClienteDTO pJugador)
    {
        if(pJugador!=null)
        {
            if(!jugadores.isEmpty())
            {
                if(!jugadores.contains(pJugador))
                {
                    jugadores.add(pJugador);
                }
            }
            else
            {
                jugadores.add(pJugador);
            }
        }
    }
    /**
     * devuelve la lista de jugadores
     * @return jugadores
     */
    public ArrayList<ClienteDTO> getJugadores()
    {
        return jugadores;
    }
    /**
     * devuelve un jugador con id dado
     * @param id el identificador del jugador
     * @return jugador o null
     */
    public ClienteDTO getJugador(String id)
    {
        for(ClienteDTO c: jugadores)
        {
            if(c.getId().equals(id))
            {
                return c;
            }
        }
        return null;
    }
    /**
     * elimina a los jugadores
     */
    public void deleteJugadores()
    {
        jugadores.clear();
    }

    public ArrayList<PartidoDTO> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<PartidoDTO> partidos) {
        this.partidos = partidos;
    }
//
//    public ClienteDTO getRepresentante() {
//        return representante;
//    }
//
//    public void setRepresentante(ClienteDTO representante) {
//        this.representante = representante;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * elimina a un jugador con id dado
     * @param id el identificador del jugador a eliminar
     */
    public void deleteJugador(String id)
    {
        ClienteDTO temp =this.getJugador(id);
        if(temp!=null)
        {
            jugadores.remove(temp);
        }
    }
}
