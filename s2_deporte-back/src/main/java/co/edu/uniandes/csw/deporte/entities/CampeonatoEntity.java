/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author estudiante
 */
@Entity
public class CampeonatoEntity extends BaseEntity implements Serializable{
    private List<Integer> puntos;

    private String nombre;
    @OneToOne
    private BlogEntity blog;
    
    @OneToMany(mappedBy = "campeonato")
    private List<PostEntity> posts;
    

   

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(List<Integer> puntos) {
        this.puntos = puntos;
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

    /**
     * @return the posts
     */
    public List<PostEntity> getPosts() {
        return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

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
}
