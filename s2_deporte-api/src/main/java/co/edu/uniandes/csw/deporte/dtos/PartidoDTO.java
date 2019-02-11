/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class PartidoDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * la fecha que se realizar el partido
     */
    private Date fecha;
    /** 
     * el identificador del partido
     */
    private String id;
    //Constructor---------------------------------------------------------------
    public PartidoDTO()
    {
        
    }
    //Métodos-------------------------------------------------------------------
    private void setId(String pId)
    {
        id=pId;
    }
    private String getId()
    {
        return id;
    }
    private void setFecha(Date pFecha)
    {
        fecha=pFecha;
    }
    private Date getFecha()
    {
        return fecha;
    }
}
