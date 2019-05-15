/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class PropietarioEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    private Integer numCanchas;
    private String imagen;
    private String contrasena;
    @OneToMany
    @PodamExclude
    private List<CanchaEntity> canchas;
    


    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the numCanchas
     */
    public Integer getNumCanchas() {
        return numCanchas;
    }

    /**
     * @param numCanchas the numCanchas to set
     */
    public void setNumCanchas(Integer numCanchas) {
        this.numCanchas = numCanchas;
    }

    /**
     * @return the canchas
     */
    public List<CanchaEntity> getCanchas() {
        return canchas;
    }

    /**
     * @param canchas the canchas to set
     */
    public void setCanchas(List<CanchaEntity> canchas) {
        this.canchas = canchas;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
