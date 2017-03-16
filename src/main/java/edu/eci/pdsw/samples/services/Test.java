/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;

/**
 *
 * @author hcadavid
 */
public class Test {

    public static void main(String a[]) throws ExcepcionServiciosAlquiler{
        
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 1, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");
        //Item i2=new Item(sa.consultarTipoItem(1), 2, "Halo 3", "Halo 3 es un videojuego de disparos en primera persona desarrollado por Bungie Studios.", java.sql.Date.valueOf("2007-09-08"), 3000, "DVD", "Shooter");
        
        sa.registrarCliente(new Cliente("Juan Perez",123,"24234","calle 123","aa@gmail.com"));
        sa.registrarItem(i1);
        //sa.registrarItem(i2);
        
        Item item=sa.consultarItem(1);
        
        System.out.println(sa.consultarItemsDisponibles());
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-01"), 123, item, 5);
        
        System.out.println(sa.consultarMultaAlquiler(1, java.sql.Date.valueOf("2005-12-06")));
        
        //sa.registrarAlquilerCliente(java.sql.Date.valueOf("2017-01-01"), 123, , 0);
        
    }
    
}
