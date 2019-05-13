/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;
import co.edu.uniandes.csw.deporte.entities.ClienteEntity;
import co.edu.uniandes.csw.deporte.entities.EquipoEntity;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @cliente estudiante
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable
{
    //Atributos-----------------------------------------------------------------
    /**
     * equipos a los que pertenece un cliente
     */
    private List<EquipoDTO> equipos;
    private EquipoDTO representa;
    private List<PostDTO> posts;
    private List<CampeonatoDTO> campeonatos;
    //Constructor---------------------------------------------------------------
    public ClienteDetailDTO()
    {
        super();
    }
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        if (entity != null) {
            equipos = new ArrayList<>();
            for (EquipoEntity entityEquipos : entity.getEquipos()) {
                equipos.add(new EquipoDTO(entityEquipos));
            }

        }

    }
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
        if (equipos != null) {
            List<EquipoEntity> equiposEntity = new ArrayList<>();
            for (EquipoDTO dtoEquipo : equipos) {
                equiposEntity.add(dtoEquipo.toEntity());
            }
            entity.setEquipos(equiposEntity);
        }

        return entity;
    }
    //Método--------------------------------------------------------------------

    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }

    public EquipoDTO getRepresenta() {
        return representa;
    }

    public void setRepresenta(EquipoDTO representa) {
        this.representa = representa;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<CampeonatoDTO> getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(List<CampeonatoDTO> campeonatos) {
        this.campeonatos = campeonatos;
    }
    
}
