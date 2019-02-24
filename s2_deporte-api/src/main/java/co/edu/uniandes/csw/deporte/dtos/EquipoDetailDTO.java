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
    private ArrayList<ClienteDTO> jugadores;
    
    private ArrayList<PartidoDTO> partidos;
    //Constructor---------------------------------------------------------------
    public EquipoDetailDTO()
    {
        super();
    }
    public EquipoDetailDTO(EquipoEntity entity) {
        super(entity);
        if (entity != null) {
            jugadores = new ArrayList<>();
            //for (ClienteEntity entityClientes : entity.getJugadores()) {
              //  jugadores.add(new ClienteDTO(entityClientes));
            //}
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
            //entity.setJugadores(clientesEntity);
            
            List<PartidoEntity> partidosEntity = new ArrayList<>();
            for (PartidoDTO dtoPartido : partidos) {
                partidosEntity.add(dtoPartido.toEntity());
            }
            //entity.setJugadores(clientesEntity);
        }

        return entity;
    }
    //Métodos-------------------------------------------------------------------
    /**
     * agrega un jugador a la lista de jugadores
     * @param pJugador el jugador a agregar, pJugador!=null && pJugador!cjugadores
     */
    private void addJugador(ClienteDTO pJugador)
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
    private ArrayList<ClienteDTO> getJugadores()
    {
        return jugadores;
    }
    /**
     * devuelve un jugador con id dado
     * @param id el identificador del jugador
     * @return jugador o null
     */
    private ClienteDTO getJugador(String id)
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
    private void deleteJugadores()
    {
        jugadores.clear();
    }
    /**
     * elimina a un jugador con id dado
     * @param id el identificador del jugador a eliminar
     */
    private void deleteJugador(String id)
    {
        ClienteDTO temp =this.getJugador(id);
        if(temp!=null)
        {
            jugadores.remove(temp);
        }
    }
}
