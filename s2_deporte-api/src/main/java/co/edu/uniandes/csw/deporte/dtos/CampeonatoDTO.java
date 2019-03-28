/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Camilo Garcia
 */
public class CampeonatoDTO implements Serializable{
    
    public List<Integer> puntos;
    
    public Long id;
    
    public String nombre;
    
    public String descripcion;
    
    public BlogDTO blogDTO;
    
    public CampeonatoDTO()
    {
        
    }

     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param campeonatoEntity: Es la entidad que se va a convertir a DTO
     */
    public CampeonatoDTO(CampeonatoEntity campeonatoEntity) {
        if (campeonatoEntity != null) {
            this.id = campeonatoEntity.getId();
            this.nombre = campeonatoEntity.getNombre();
            this.descripcion = campeonatoEntity.getDescripcion();
            if(campeonatoEntity.getBlog() != null)
            {
                this.blogDTO = new BlogDTO(campeonatoEntity.getBlog());
            }
            else
            {
                this.blogDTO = null;
            }
        }
    }
    /**
     * @return the puntos
     */
    public List<Integer> getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(ArrayList<Integer> puntos) {
        this.setPuntos(puntos);
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
     * @return the blogDTO
     */
    public BlogDTO getBlogDTO() {
        return blogDTO;
    }

    /**
     * @param blogDTO the blogDTO to set
     */
    public void setBlogDTO(BlogDTO blogDTO) {
        this.blogDTO = blogDTO;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public CampeonatoEntity toEntity() {
        CampeonatoEntity campeonatoEntity = new CampeonatoEntity();
        campeonatoEntity.setId(this.getId());
        campeonatoEntity.setNombre(this.getNombre());
        if(this.blogDTO != null)
       {
            campeonatoEntity.setBlog(this.blogDTO.toEntity());
        }
        return campeonatoEntity;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(List<Integer> puntos) {
        this.puntos = puntos;
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
