/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import java.io.Serializable;

/**
 *
 * @author Santiago Serrano
 */
public class PropietarioDTO implements Serializable{
    
    private Long id;
    
    private String nombre;
    
    private Integer numCanchas;
    
    private String imagen;
    
    private String contrasena;
    
    public PropietarioDTO () {
        
    }
    
        
        /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param propietarioEntity: Es la entidad que se va a convertir a DTO
     */
    public PropietarioDTO(PropietarioEntity propietarioEntity) {
        if (propietarioEntity != null) {
            this.id = propietarioEntity.getId();
            this.nombre=propietarioEntity.getNombre();
            this.numCanchas = propietarioEntity.getNumCanchas();
            this.imagen = propietarioEntity.getImagen();
            this.contrasena =propietarioEntity.getContrasena();
        }
    }

    /**
     * @return the numCanchas
     */
    public Integer getNumCanchas() {
        return numCanchas;
    }

    /**
     * @param numCanchas the numCanchas to set
     */
    public void setNumCanchas(Integer numCanchas) {
        this.numCanchas = numCanchas;
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
    public PropietarioEntity toEntity() {
        PropietarioEntity propietarioEntity = new PropietarioEntity();
        propietarioEntity.setNumCanchas(this.getNumCanchas());
        propietarioEntity.setId(this.id);
        propietarioEntity.setNombre(this.getNombre());
        propietarioEntity.setImagen(this.imagen);
        propietarioEntity.setContrasena(this.getContrasena());
        return propietarioEntity;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
