/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Camilo Garcia
 */
@Entity
public class PostEntity extends BaseEntity implements Serializable {
@Column(name="Nombre")
private String nombre;  
@Column(name="contenido")
private String contenido;

    @PodamExclude
    @ManyToOne
    private BlogEntity blog;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    public PostEntity()
    {
       
    }

    /**
     * @return the identificador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setNombre(String identificador) {
        this.nombre = identificador;
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
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
   }

