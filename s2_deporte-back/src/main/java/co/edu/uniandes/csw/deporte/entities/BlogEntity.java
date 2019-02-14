/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable{
    private int identificador;

    /**
     * @return the id
     */
    public int getIdentidicador() {
        return identificador;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.identificador = id;
    }
}
