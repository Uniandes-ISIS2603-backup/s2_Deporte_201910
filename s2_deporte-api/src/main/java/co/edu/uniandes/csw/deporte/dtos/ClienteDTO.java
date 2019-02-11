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
public class ClienteDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * identificador del cliente
     */
    private String id;
    //Constructor---------------------------------------------------------------
    public ClienteDTO()
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
}
