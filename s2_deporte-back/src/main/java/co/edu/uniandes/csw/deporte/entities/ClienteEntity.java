/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Nicolas Poch
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
        
    @PodamExclude
    @ManyToMany(mappedBy="jugadores")
    private List<EquipoEntity> equipos = new ArrayList<>();
    
    @PodamExclude
    @OneToOne(mappedBy="representante",fetch=FetchType.EAGER)
    private EquipoEntity representa;
    
    private String nombre;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<PostEntity> posts = new ArrayList<>();
    
    @PodamExclude
    @ManyToMany(mappedBy="clientes")
    private List<CampeonatoEntity> campeonatos;
    
    public ClienteEntity()
    {
        this.campeonatos = new ArrayList<>();
        
    }  

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }
    /**
     * devuelve la la lista de equipos
     * @return equipos
     */
    public List<EquipoEntity> getEquipos()
    {
        return equipos;
    }
    /**
     * modifica la lista de equipos
     * @param pEquipos los nuevos equipos
     */
    public void setEquipos(List<EquipoEntity> pEquipos)
    {
        equipos=pEquipos;
    }
    
    /**
     * devuelve el nombre del cliente
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * modifica el nombre del cliente
     * @param nombre el nombre que se le va a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

