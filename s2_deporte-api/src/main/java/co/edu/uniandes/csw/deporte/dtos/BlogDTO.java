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
    
//      public CampeonatoDTO campeonatoDTO;
      
      public String nombre;
      
      public String descripcion;
      
      public Long id;
      
      
    public BlogDTO()
    {
        
    }
    
    public BlogDTO(BlogEntity blogEntity)
    {
        if (blogEntity != null) {
            this.id = blogEntity.getId();
            this.nombre = blogEntity.getNombre();
            this.descripcion = blogEntity.getDescripcion();
           
//            
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
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BlogEntity toEntity() {
        BlogEntity bloglEntity = new BlogEntity();
        bloglEntity.setId(this.getId());
        bloglEntity.setNombre(this.getNombre());
        bloglEntity.setDescripcion(this.descripcion);

        return bloglEntity;
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
