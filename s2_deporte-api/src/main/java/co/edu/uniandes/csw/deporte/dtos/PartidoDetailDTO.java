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
public class PartidoDetailDTO extends PartidoDTO
{
    //Atributos-----------------------------------------------------------------
    private ArrayList<EquipoDTO> equipos;
    private ArrayList<Integer> puntaje;
    //Constructor---------------------------------------------------------------
    public PartidoDetailDTO()
    {
        
    }
    //Métodos-------------------------------------------------------------------
    private ArrayList<EquipoDTO> getEquipos()
    {
        return equipos;
    }
    private void addEquipo(EquipoDTO e)
    {
        if(equipos.size()<2)
        {
            equipos.add(e);
        }
    }
    private void deleteEquipos()
    {
        equipos.clear();
    }
}
