/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class PartidoDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * la fecha que se realizar el partido
     */
    private Date fecha;
    /** 
     * el identificador del partido
     */
    private Long id;
    //Constructor---------------------------------------------------------------
    public PartidoDTO()
    {
        
    }
    public PartidoDTO(PartidoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fecha=entity.getFecha();
        }
    }

    /**
     * Convierte un objeto ClienteDTO a ClienteEntity.
     *
     * @return Nueva objeto ClienteEntity.
     * 
     */
    public PartidoEntity toEntity() {
        PartidoEntity entity = new PartidoEntity();
        entity.setId(this.getId());
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
    public void setFecha(Date pFecha)
    {
        fecha=pFecha;
    }
    public Date getFecha()
    {
        return fecha;
    }
}
