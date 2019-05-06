/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.CanchaEntity;
import co.edu.uniandes.csw.deporte.entities.PropietarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class PropietarioDetailDTO extends PropietarioDTO implements Serializable {

    private List<CanchaDTO> canchas;

    public PropietarioDetailDTO() {
        super();
    }

    public PropietarioDetailDTO(PropietarioEntity propietario) {
        super(propietario);
        if (propietario != null) {
            canchas = new ArrayList<>();
            for (CanchaEntity entityCancha : propietario.getCanchas()) {
                canchas.add(new CanchaDTO(entityCancha));
            }
        }
    }
    
    /**
     * @return the canchas
     */
    public List<CanchaDTO> getCanchas() {
        return canchas;
    }
    
    /**
     * @param canchas the canchas to set
     */
    public void setCanchas(List<CanchaDTO> canchas) {
        this.canchas = canchas;
    }
    
    @Override
    public PropietarioEntity toEntity(){
        PropietarioEntity entityPropietario = super.toEntity();
        if(canchas!=null){
            List<CanchaEntity> canchasEntitys = new ArrayList();
            for(CanchaDTO cancha:canchas){
                canchasEntitys.add(cancha.toEntity());
            }
            entityPropietario.setCanchas(canchasEntitys);
        }
        return entityPropietario;
    }
}
