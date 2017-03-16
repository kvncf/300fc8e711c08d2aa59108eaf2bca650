/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class ClientesTest {

    
    
    /*
    Clases de equivalencia: 
    precondicion: nueco cliente no puede estar nulo
    poscondicion: el cliente queda disponible para futuros alquileres
    
    
    #CE |        CE                                           | Tipo          | Resultado 
    
    1    p!= null                                               Error               ninguno
    2    el cliente ya se encuentra 
         p existe clientes                                      Dentro Frontera     No se agrega P
    3    el cliente no se encuentra 
         p existe clientes                                      Dentro Frontera     Se agrega P
  
    
    
    */
    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void CE3Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sev=ServiciosAlquilerItemsStub.getInstance();        
        Cliente cl = new Cliente("Antonio Moros",2425,"1234","av suba miranda","mosca@gmail.com");   
        
        boolean exis = false; // si ya existe P: true
        if(sev.consultarCliente(1581)==cl) exis = true;
        sev.registrarCliente(cl);    
       
        assertEquals("Se esta creando",sev.consultarCliente(2425)==cl,true);
        assertEquals("No esta regsitrado",exis, false);   	
    }
    
    
    
     @Test
    public void CE2Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sev=ServiciosAlquilerItemsStub.getInstance();        
        Cliente cl = new Cliente("Antonio Carros",2426,"1234","av caracol","mosca23@gmail.com");  
        sev.registrarCliente(cl); 
        boolean falla = false;
        boolean exis = false; // si ya existe P: true
        if(sev.consultarCliente(2426)==cl) exis = true;
        
        try{
            sev.registrarCliente(cl); 
        }catch(ExcepcionServiciosAlquiler e){           
            falla=true;
        }
        assertEquals("No se egrega",falla,true);
        assertEquals("Ya esta regsitrado",exis, true);   	
    }
    
    
     @Test
    public void CE1Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sev=ServiciosAlquilerItemsStub.getInstance();        
        Cliente cl = new Cliente("Antonio Moros",1581,"1234","av suba miranda","mosca@gmail.com");        
        boolean falla=false;
        try{
            sev.registrarCliente(cl);             
           
        }catch(ExcepcionServiciosAlquiler e){           
            falla=true;
        }
        assertEquals("No es adicionado un cliente nulo",falla,false);           	
    }
    
    
    
    
    
}
