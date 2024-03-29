/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Camilo Garcia
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable{
    private Integer identificador;
    
    private String nombre;
    
    private String descripcion;
    
    private String rutaImagen;
    
    @PodamExclude
    @OneToMany(mappedBy = "blog", cascade = CascadeType.PERSIST)
    private List<PostEntity> posts = new ArrayList<>();
    

    
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
        this.setIdentificador((Integer) id);
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

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * @param rutaImagen the rutaImagen to set
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}