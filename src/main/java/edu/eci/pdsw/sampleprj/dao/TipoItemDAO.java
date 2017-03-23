/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.TipoItem;

/**
 *
 * @author hcadavid
 */
public interface TipoItemDAO {
    
    public TipoItem load(int id) throws PersistenceException;
    
}
