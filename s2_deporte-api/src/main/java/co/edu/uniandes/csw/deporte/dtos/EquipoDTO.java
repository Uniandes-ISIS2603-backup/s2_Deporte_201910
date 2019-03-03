/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class EquipoDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * el representante del equipo
     */
    private ClienteDTO representante;
    /**
     * el identificador del equipo
     */
    private Long id;

    
    private String nombre;
    //Constructor---------------------------------------------------------------
    public EquipoDTO()
    {
        
    }
    public EquipoDTO(EquipoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();   
            this.representante=new ClienteDTO(entity.getRepresentante());
        }
    }

    /**
     * Convierte un objeto ClienteDTO a ClienteEntity.
     *
     * @return Nueva objeto ClienteEntity.
     * 
     */
    public EquipoEntity toEntity() {
        EquipoEntity entity = new EquipoEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setRepresentante(representante.toEntity());
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
    private void setRepresentante(ClienteDTO pRepresentante)
    {
        representante=pRepresentante;
    }
    private ClienteDTO getRepresentante()
    {
        return representante;
    }
    private void deleteRepresentante()
    {
        representante=null;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
