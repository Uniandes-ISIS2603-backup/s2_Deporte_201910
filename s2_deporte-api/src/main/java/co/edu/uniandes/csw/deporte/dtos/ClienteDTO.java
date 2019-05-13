/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import java.io.Serializable;

/**
 *
 * @cliente estudiante
 */
public class ClienteDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * identificador del cliente
     */
    private Long id;
    /**
     * el nombre del cliente
     */
    private String nombre;
    /**
     * nombre de usuario
     */
    private String user;
    /**
     * clave del cliente
     */
    private String clave;
    
    //Constructor---------------------------------------------------------------
    public ClienteDTO()
    {
        
    }
    public ClienteDTO(ClienteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();             
        }
    }

    /**
     * Convierte un objeto ClienteDTO a ClienteEntity.
     *
     * @return Nueva objeto ClienteEntity.
     * 
     */
    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        
        return entity;
    }
    //Métodos-------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
