/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author estudiante
 */
@Entity
public class PostEntity extends BaseEntity implements Serializable {
    private String id;

    /**
     * @return the id
     */
    public String getIdd() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setIdd(String id) {
        this.id = id;
    }
}
