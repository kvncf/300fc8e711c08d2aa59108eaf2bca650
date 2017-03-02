


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



@ManagedBean(name = "beanEstadoAdivinanza")
//@SessionScoped
@ApplicationScoped

/**
 *
 * @author AlvaroPerez
 */
public class BackBean {
    
    private final int PREMIOMAX=100000;
    private final int PIERDE=10000;
    private int adivinar=-1;
    private int intentos;
    private int premio=100000;
    private int numero;   
    private String estado;
    
    public void reiniciar(){
        adivinar=(int)(Math.random() * 10);
        intentos=0;
        premio=PREMIOMAX;
        estado="adivina el numero";
    }
    
    public void jugar(){
        if(adivinar==-1)reiniciar();
        intentos++;
        if(numero!=adivinar){
            premio-= PIERDE;
            estado="numero in correcto ";
        }
        else{
            estado="Ganaste :) :9 ";
            adivinar=-1;
        }
    }

    public int getAdivinar() {
        
        return adivinar;
    }

    public int getIntentos() {
        return intentos;
    }

    
    public int getPremio() {
        return premio;
    }

    public String getEstado() {
        return estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
