/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Camilo Garcia
 */
@Entity
public class CampeonatoEntity extends BaseEntity implements Serializable{
    private List<Integer> puntos;

    private String nombre;
    
    private String descripcion;
    @OneToOne
    private BlogEntity blog;
    
    @PodamExclude
    @ManyToMany
    private List<ClienteEntity> clientes= new ArrayList<>();
    
    
   
    

   

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(List<Integer> puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the blog
     *
    public BlogEntity getBlog() {
        return blog;
    }
    /* 

    /**
     * @param blog the blog to set
     *
    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }
/*
    /**
     * @return the puntos
     */
    public List<Integer> getPuntos() {
        return puntos;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
