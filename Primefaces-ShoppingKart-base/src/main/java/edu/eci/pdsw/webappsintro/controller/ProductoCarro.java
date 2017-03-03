/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.webappsintro.controller;

import edu.eci.pdsw.stubs.servicesfacadestub.Producto;
import java.io.Serializable;
/**
 *
 * @author AlvaradoPerez
 */
public class ProductoCarro implements Serializable{
    private int cantidad=0;
    private Producto producto; 
    

    public ProductoCarro(Producto producto) {
        this.producto=producto;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }  
    
    
}
