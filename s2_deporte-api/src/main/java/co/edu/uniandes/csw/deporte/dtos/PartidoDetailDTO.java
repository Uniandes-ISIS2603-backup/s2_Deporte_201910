/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import co.edu.uniandes.csw.deporte.entities.PartidoEntity;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author estudiante
 */
public class PartidoDetailDTO extends PartidoDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * los equipos que participan
     */
    private List<EquipoDTO> equipos;
    /**
     * el puntaje
     */
    private List<Integer> puntaje;
    //Constructor---------------------------------------------------------------
    public PartidoDetailDTO()
    {
        super();
    }
    public PartidoDetailDTO(PartidoEntity entity) {
        super(entity);
        if (entity != null) {
            equipos = new ArrayList<>();
            for (EquipoEntity entityEquipos : entity.getEquipos()) {
                equipos.add(new EquipoDTO(entityEquipos));
            }
        }
    }
    @Override
    public PartidoEntity toEntity() {
        PartidoEntity entity = super.toEntity();
        if (equipos != null) {
            List<EquipoEntity> equiposEntity = new ArrayList<>();
            for (EquipoDTO dtoEquipo : equipos) {
                equiposEntity.add(dtoEquipo.toEntity());
            }
            entity.setEquipos(equiposEntity);
            
        }

        return entity;
    }
    //Métodos-------------------------------------------------------------------

    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }

    public List<Integer> getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(List<Integer> puntaje) {
        this.puntaje = puntaje;
    }
    
}
