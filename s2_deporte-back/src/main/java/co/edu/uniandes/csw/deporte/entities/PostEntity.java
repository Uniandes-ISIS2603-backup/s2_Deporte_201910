/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Juan Camilo Garcia
 */
@Entity
public class PostEntity extends BaseEntity implements Serializable {
    
    private String identificador;
    
   // @ManyToOne
    //private BlogEntity blog;
    
    public PostEntity()
    {
       
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the blog
     */
   // public BlogEntity getBlog() {
     //   return blog;
    //}

    /**
     * @param blog the blog to set
     */
   // public void setBlog(BlogEntity blog) {
     //   this.blog = blog;
    //}
   }

