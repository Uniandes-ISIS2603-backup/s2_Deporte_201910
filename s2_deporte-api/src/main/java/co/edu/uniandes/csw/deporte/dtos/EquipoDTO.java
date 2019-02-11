/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class EquipoDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * el representante del equipo
     */
    private ClienteDTO representante;
    /**
     * el identificador del equipo
     */
    private String id;
    //Constructor---------------------------------------------------------------
    public EquipoDTO()
    {
        
    }
    //Métodos-------------------------------------------------------------------
    
    private void setId(String pId)
    {
        id=pId;
    }
    String getId()
    {
        return id;
    }
    private void setRepresentante(ClienteDTO pRepresentante)
    {
        representante=pRepresentante;
    }
    private ClienteDTO getRepresentante()
    {
        return representante;
    }
    private void deleteRepresentante()
    {
        representante=null;
    }
}
