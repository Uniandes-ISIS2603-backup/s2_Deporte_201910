/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class CampeonatoDTO implements Serializable{
    
    private ArrayList<Integer> puntos;
    
    private int id;
    
    private BlogDTO blogDTO;
    public CampeonatoDTO()
    {
        
    }

    /**
     * @return the puntos
     */
    public ArrayList<Integer> getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(ArrayList<Integer> puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
