/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

import java.util.Date;
import javax.persistence.Entity;



/**
 *
 * @author Santiago Barbosa
 */
@Entity
public class ReservaEntity {
    
    private Date fechaInicio;
    private Date fechaFin;
}
