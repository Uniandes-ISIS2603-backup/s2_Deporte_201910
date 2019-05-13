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
    /**
     * el nombre del cliente
     */
    private String nombre;
    /**
     * nombre de usuario
     */
    private String usern;
    /**
     * clave del cliente
     */
    private String clave;    
    
    @PodamExclude
    @ManyToMany(mappedBy ="jugadores", cascade=CascadeType.PERSIST)
    private List<EquipoEntity> equipos = new ArrayList<>();
    
    @PodamExclude
    @OneToOne(mappedBy= "representante",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private EquipoEntity representa;
        
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade= CascadeType.PERSIST ,orphanRemoval=true)
    private List<PostEntity> posts = new ArrayList<>();
    
    @PodamExclude
    @ManyToMany(mappedBy="clientes", cascade=CascadeType.PERSIST)
    private List<CampeonatoEntity> campeonatos= new ArrayList<>();
    
    public List<PostEntity> getPosts() {
        return posts;
    }

    public EquipoEntity getRepresenta() {
        return representa;
    }

    public void setRepresenta(EquipoEntity representa) {
        this.representa = representa;
    }
    
    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public List<CampeonatoEntity> getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(List<CampeonatoEntity> campeonatos) {
        this.campeonatos = campeonatos;
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

    public String getUser() {
        return usern;
    }

    public void setUser(String user) {
        this.usern = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}

