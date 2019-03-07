/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.persistence.EquipoPersistence;
import co.edu.uniandes.csw.deporte.persistence.PartidoPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class EquipoPartidoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(EquipoPartidoLogic.class.getName());
    
    @Inject
    private EquipoPersistence equipoPersistence;
    
    @Inject
    private PartidoPersistence partidoPersistence;
}
