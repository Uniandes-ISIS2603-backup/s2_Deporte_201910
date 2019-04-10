/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Camilo Garcia
 */
@Entity
public class PostEntity extends BaseEntity implements Serializable {
    
private String descripcion;    
    @PodamExclude
    @ManyToOne
    public BlogEntity blog;
    
    @PodamExclude
    @ManyToOne
    public ClienteEntity cliente;
    
    public PostEntity()
    {
       
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return descripcion;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.descripcion = identificador;
    }

    /**
     * @return the blog
     */
    public BlogEntity getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }
   }

