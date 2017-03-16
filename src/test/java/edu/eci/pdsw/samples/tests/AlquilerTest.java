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
 * Calculo Multa:
 * 
 * Frontera:
 * CF1: Multas a devoluciones hechas en la fecha exacta (multa 0).
 * CF2: Multas a devoluciones hechas en la fecha exacta que se tomó la película (multa 0).
 * 
 * Clases de equivalencia:
 * CE1: Multas hechas a devolciones realizadas en fechas posteriores a la limite. (multa multa_diaria*dias_retraso)
 * CE2: No debe aceptar dias de prestamo cero
 * CE3: No debe permitir colocar fecha de alquiler menor a la actual
 * CE4: No debe permitir colocar fecha de devolución menor a la del pedido de la película
 * 
 * 
 * 
 */
public class AlquilerTest {

    public AlquilerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void CF1Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 44, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",3842,"24234","calle 123","aa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(44);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2025-12-20"), 3842, item, 5);
        
        assertEquals("No se calcula correctamente la multa (0) "
                + "cuando la devolucion se realiza el dia limite."
                ,0,sa.consultarMultaAlquiler(44, java.sql.Date.valueOf("2025-12-25")));
                
    }
    

    @Test
    public void CE1Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 55, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",9843,"24234","calle 123","aa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(55);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2025-12-20"), 9843, item, 5);
        //prueba: 3 dias de retraso
        assertEquals("No se calcula correctamente la multa "
                + "cuando la devolucion se realiza varios dias despues del limite."
                ,sa.valorMultaRetrasoxDia()*3,sa.consultarMultaAlquiler(55, java.sql.Date.valueOf("2025-12-28")));

    }

    
    @Test
    public void CF2Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 11, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",3845,"24234","calle 123","aya@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(11);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2025-12-20"), 3842, item, 5);
        
        assertEquals("No se calcula correctamente la multa (0) "
                + "cuando la devolucion se realiza el mismo día de prestamo."
                ,0,sa.consultarMultaAlquiler(11, java.sql.Date.valueOf("2025-12-20")));
                
    }
    
    

    @Test
    public void CE2Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 55, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",9844,"24234","calle 123","axa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(55);
        boolean falla=false;
        try{
            sa.registrarAlquilerCliente(java.sql.Date.valueOf("2025-12-20"), 9843, item, 0);
        }catch(ExcepcionServiciosAlquiler e){
            //prueba: 0 dias de préstamo
            falla=true;
        }
        assertEquals("Se están aceptando los días de préstamo cero.",falla,true);

    }
    
    @Test
    public void CE3Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 66, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",98452,"24234","calle 123","axa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(66);
        boolean falla=false;
        try{
            sa.registrarAlquilerCliente(java.sql.Date.valueOf("2016-12-20"), 9843, item, 5);
        }catch(ExcepcionServiciosAlquiler e){
            //prueba: fecha de prestamo de registro vieja
            falla=true;
        }
        assertEquals("Se están aceptando fechas de prestamo menor a hoy.",falla,true);

    }
    
    @Test
    public void CE4Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 88, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",98472,"24234","calle 123","axa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(88);
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2026-12-20"), 9843, item, 5);
        
        boolean falla=false;
        try{
            sa.consultarMultaAlquiler(88, java.sql.Date.valueOf("2025-12-20"));
        }catch(ExcepcionServiciosAlquiler e){
            //prueba: fecha de multa menor a la de alquiler
            falla=true;
        }
        assertEquals("Se están aceptando fechas de multa menor a la del alquiler.",falla,true);
    }
}
