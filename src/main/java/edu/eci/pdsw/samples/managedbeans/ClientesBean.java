/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AlvaradoPerez
 */
@ManagedBean(name = "ClientesBen")
@SessionScoped
public class ClientesBean implements Serializable {

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    private Cliente clienteSeleccionado;
    private List<Cliente> listaClientes;
    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private boolean reverse;
    

    
    public ClientesBean() throws ExcepcionServiciosAlquiler {
        listaClientes = sp.consultarClientes();
        clienteSeleccionado = null;
        reverse = false;
    }
    
    
    public List<Cliente> getListaClientes() throws ExcepcionServiciosAlquiler {
        
        listaClientes = sp.consultarClientes();   
        List<Cliente> lista = listaClientes;
        if(!reverse && lista.size()>1 ) {
            reverse=true;
            Collections.reverse(lista);        
        }
        
        return lista;
    }

     

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
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
    
    public void registrarCliente() throws ExcepcionServiciosAlquiler {
        Cliente cliente = new Cliente(nombre, documento, telefono, direccion, email);
        sp.registrarCliente(cliente);       
        clean();
        
    }
    
    
    private void clean(){
        this.nombre=null;
        this.documento= 0;
        this.telefono=null;
        this.direccion=null;
        this.email=null;
    }
    

    
    
    
}
