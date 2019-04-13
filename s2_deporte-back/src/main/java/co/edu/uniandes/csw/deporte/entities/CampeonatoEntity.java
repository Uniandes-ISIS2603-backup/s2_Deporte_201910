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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Camilo Garcia
 */
@Entity
public class CampeonatoEntity extends BaseEntity implements Serializable{
//    public List<Integer> puntos;

    private String nombre;
    
    private String descripcion;
    
    private String rutaImagen;
    
    private String deporte;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private BlogEntity blog;
    
    @PodamExclude
    @ManyToMany
    private List<ClienteEntity> clientes= new ArrayList<>();

    /**
     * @param puntos the puntos to set
     */
//    public void setPuntos(List<Integer> puntos) {
//        this.puntos = puntos;
//    }

    /**
     * @return the blog
    */
//    public BlogEntity getBlog() {
//        return blog;
//    }

    /**
     * @param blog the blog to set
     */
//    public void setBlog(BlogEntity blog) {
//        this.blog = blog;
//    }
    /**
     * @return the puntos
     */
//    public List<Integer> getPuntos() {
//        return puntos;
//    }

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
     * @return the clientes
     */
    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the deporte
     */
    public String getDeporte() {
        return deporte;
    }

    /**
     * @param deporte the deporte to set
     */
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
}
