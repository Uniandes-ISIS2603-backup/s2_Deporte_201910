/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Camilo Garcia
 */
public class CampeonatoDTO implements Serializable{
    
    //id del campeonato
    private Long id;
    
    //nombre del campeonato
    private String nombre;
    
    //Descripcion del blog
    private String descripcion;
    
    //Ruta de la imagen de el campeonato
    private String rutaImagen;
    
    //Deporte que se juega en el campeonato.
    private String deporte;
    
    
    private BlogDTO blogDTO;
    
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
            this.rutaImagen = campeonatoEntity.getRutaImagen();
            this.deporte = campeonatoEntity.getDeporte();

        }
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
        campeonatoEntity.setDescripcion(this.getDescripcion());
        campeonatoEntity.setRutaImagen(this.getRutaImagen());
        campeonatoEntity.setDeporte(this.deporte);

        return campeonatoEntity;
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
