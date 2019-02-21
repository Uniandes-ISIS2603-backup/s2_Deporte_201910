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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable{
    private int identificador;
    
    private String nombre;
@PodamExclude
    @OneToMany(mappedBy = "blog")
    private List<PostEntity> posts;
    
    @OneToOne
    private CampeonatoEntity campeonato;  
    
    /**
     * @return the id
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param id the id to set
     */
    public void setIdentificador(int id) {
        this.identificador = id;
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
     * @return the campeonato
     */
    public CampeonatoEntity getCampeonato() {
        return campeonato;
    }

    /**
     * @param campeonato the campeonato to set
     */
    public void setCampeonato(CampeonatoEntity campeonato) {
        this.campeonato = campeonato;
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