/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;
import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @cliente estudiante
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * equipos a los que pertenece un cliente
     */
    private ArrayList<EquipoDTO> equipos;
    //Constructor---------------------------------------------------------------
    public ClienteDetailDTO()
    {
        super();
    }
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        if (entity != null) {
            equipos = new ArrayList<>();
            for (EquipoEntity entityEquipos : entity.getEquipos()) {
                equipos.add(new EquipoDTO(entityEquipos));
            }

        }

    }
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
        if (equipos != null) {
            List<EquipoEntity> equiposEntity = new ArrayList<>();
            for (EquipoDTO dtoEquipo : equipos) {
                equiposEntity.add(dtoEquipo.toEntity());
            }
            entity.setEquipos(equiposEntity);
        }

        return entity;
    }
    //Método--------------------------------------------------------------------
    /**
     * Agrega un equipo a la lista de equipos a los cuales pertenece un cliente
     * @param pEquipo el equipo a insertar, pEquipo!=null && pEquipo!c equipos
     */
    private void addEquipo(EquipoDTO pEquipo)
    {
        if(pEquipo!=null)
        {
            if(!equipos.isEmpty())
            {
                if(!equipos.contains(pEquipo))
                {
                    equipos.add(pEquipo);
                }
            }
            else
            {
                equipos.add(pEquipo);
            }
        }
    }
    
    /**
     * borra todos los equipos de la lista 
     */
    private void deleteEquipos()
    {
        equipos.clear();
    }
    /**
     * borra un equipo con id dado
     * @param id el identificador del equipo a borrar
     */
    private void deleteEquipo(String id)
    {
        for(EquipoDTO e: equipos)
        {
            if(e.getId().equals(id))
            {
                equipos.remove(e);
                return;
            }            
        }
    }
    public ArrayList<EquipoDTO> getEquipos() {
        return equipos;
    }
     public void setEquipos(ArrayList<EquipoDTO> equipos) {
        this.equipos = equipos;
    }
}
