/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;
import java.util.*;
/**
 *
 * @author estudiante
 */
public class ClienteDetailDTO extends ClienteDTO
{
    //Atributos-----------------------------------------------------------------
    /**
     * equipos a los que pertenece un cliente
     */
    private ArrayList<EquipoDTO> equipos;
    //Constructor---------------------------------------------------------------
    public ClienteDetailDTO()
    {
        
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
     * devuelve la lista de equipos
     * @return equipos
     */
    private ArrayList<EquipoDTO> getEquipos()
    {
        return equipos;
    }
    /**
     * devuelve un id con identificador dado
     * @param id el identificador del equipo que se busca
     * @return el equipo que se busca o null
     */
    private EquipoDTO getEquipo(String id)
    {
        for(EquipoDTO e: equipos)
        {
            if(e.getId().equals(id))
            {
                return e;
            }
        }
        return null;
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
}
