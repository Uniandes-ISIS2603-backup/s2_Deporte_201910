/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.AgendaEntity;
import co.edu.uniandes.csw.deporte.entities.FranjaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AgendaDetailDTO extends AgendaDTO implements Serializable {

    public List<FranjaDTO> franjas;

    public AgendaDetailDTO() {
        super();
    }

    public AgendaDetailDTO(AgendaEntity agenda) {
        super(agenda);
        if(agenda != null){
            franjas = new ArrayList<>();
            for (FranjaEntity entityFranja : agenda.getFranjas()) {
                franjas.add(new FranjaDTO(entityFranja));
            }
        }
    }

    /**
     * @return the franjas
     */
    public List<FranjaDTO> getFranjas() {
        return franjas;
    }

    /**
     * @param franjas the franjas to set
     */
    public void setFranjas(List<FranjaDTO> franjas) {
        this.franjas = franjas;
    }
    
    public AgendaEntity toEntity(){
        AgendaEntity entityAgenda = super.toEntity();
        if(franjas!=null){
            List<FranjaEntity> franjasEntity = new ArrayList();
            for(FranjaDTO franja : franjas){
                franjasEntity.add(franja.toEntity());
            }
            entityAgenda.setFranjas(franjasEntity);
        }
        return entityAgenda;
    }
}
