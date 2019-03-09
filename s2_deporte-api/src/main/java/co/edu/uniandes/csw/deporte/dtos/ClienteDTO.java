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
    public Long id;
    /**
     * el nombre del cliente
     */
    public String nombre;
    
    public EquipoDTO representa;
    //Constructor---------------------------------------------------------------
    public ClienteDTO()
    {
        
    }
    public ClienteDTO(ClienteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre(); 
            this.representa = new EquipoDTO(entity.getRepresenta());
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
        entity.setRepresenta(representa.toEntity());
         return entity;
    }
    //Métodos-------------------------------------------------------------------
    public void setId(Long pId)
    {
        id=pId;
    }
    
    public Long getId()
    {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
