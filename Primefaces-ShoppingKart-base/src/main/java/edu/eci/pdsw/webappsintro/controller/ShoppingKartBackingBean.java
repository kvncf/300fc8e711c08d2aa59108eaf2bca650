/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.webappsintro.controller;

import edu.eci.pdsw.stubs.servicesfacadestub.CurrencyServices;
import edu.eci.pdsw.stubs.servicesfacadestub.Producto;
import edu.eci.pdsw.stubs.servicesfacadestub.ProductsServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hcadavid
 */
@ManagedBean(name = "beanCarrito")
@SessionScoped

public class ShoppingKartBackingBean implements Serializable{
    private ArrayList<int[]> pedido=new ArrayList<int[]>();
    private Producto itemSelect = null;
    private String itemName="-X--";  

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public List<Producto> getProductos(){
        return ProductsServices.getInstance().getProductos();
    }
    
    public double getTasaCambioDolar(){
        return CurrencyServices.getInstance().getUSDExchangeRateInCOP();
    }
    
    public void actualizar(Producto prod){
        int idProd=prod.getId();
        int cant=0;
        boolean yaExiste=false;
        for(int i=0; i<pedido.size();i++){
            if(pedido.get(i)[0]==idProd){
                pedido.get(i)[1]=cant;
                yaExiste=true;
                break;
            }
        }
        if(!yaExiste){//si no habia sido agregado el producto
            int[] p={idProd,cant};
            pedido.add(p);
        }      
        
    }
    
    
    public void seleccionarItem(Producto prod){
        itemName=prod.getNombre();
        itemSelect=prod;
    }
    
    public void reiniciar(){
        itemName=":(";
    }
}
