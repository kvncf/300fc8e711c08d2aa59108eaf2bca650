/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author 2106913
 */
public class Cliente implements Serializable{
    
    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private boolean vetado;
    private ArrayList<ItemRentado> rentados; 

    public Cliente() {
    }

    public Cliente(String nombre, long documento, String telefono, String direccion, String email, boolean vetado, ArrayList<ItemRentado> rentados) {   
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.vetado = vetado;
        this.rentados = rentados;
    }

  
    public Cliente(String nombre, long documento, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.vetado = false;
        this.rentados = new ArrayList<>();
    }

           
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVetado() {
        return vetado;
    }

    public void setVetado(boolean betado) {
        this.vetado = betado;
    }

    public ArrayList<ItemRentado> getRentados() {
        return rentados;
    }

    public void setRentados(ArrayList<ItemRentado> Rentados) {
        this.rentados = Rentados;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", documento=" + documento + ", rentados=\n\t" + rentados + '}';
    }

   
    
    
}
