/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Camilo Garcia
 */
public class BlogDTO implements Serializable{
    

    //Nombre del blog
      private String nombre;
      
      //Descripcion del blog
      private String descripcion;
      
      //Ruta de la imagen del blog
      private String rutaImagen;
      
      //id del blog
      private Long id;
      
      
    public BlogDTO()
    {
        
    }
    
    //Constructor que recibe como parametro un blog entity
    public BlogDTO(BlogEntity blogEntity)
    {
        if (blogEntity != null) {
            this.id = blogEntity.getId();
            this.nombre = blogEntity.getNombre();
            this.descripcion = blogEntity.getDescripcion();
            this.rutaImagen = blogEntity.getRutaImagen();
        }
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BlogEntity toEntity() {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(this.getId());
        blogEntity.setNombre(this.getNombre());
        blogEntity.setDescripcion(this.getDescripcion());
        blogEntity.setRutaImagen(this.rutaImagen);

        return blogEntity;
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
}
