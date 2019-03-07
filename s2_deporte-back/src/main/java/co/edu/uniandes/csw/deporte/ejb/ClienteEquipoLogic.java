/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.ejb;

import co.edu.uniandes.csw.deporte.persistence.ClientePersistence;
import co.edu.uniandes.csw.deporte.persistence.EquipoPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class ClienteEquipoLogic
{
    private static final Logger LOGGER = Logger.getLogger(ClienteEquipoLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private EquipoPersistence equipoPersistence;
}
