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
package edu.eci.pdsw.webappsintro.jdbc.example.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="bdprueba";
            
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2083722;
            registrarNuevoProducto(con, suCodigoECI, "kvn alvarado", 1);            
            con.commit();
            
            // actualizar nombre
            suCodigoECI=2095112;
            registrarNuevoProducto(con, suCodigoECI, "finanzas XD", 100000000);            
            con.commit();
            
            cambiarNombreProducto(con, suCodigoECI, "Nuevo Nombre F");
            con.commit();
            
            System.out.println("------total de pedido--------");
            System.out.println(valorTotalPedido(con,1));
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio){
        
        PreparedStatement updateSales = null;

        String updateString ="insert into ORD_PRODUCTOS (codigo,nombre,precio) values (?,?,?)";
        
        try {
            con.setAutoCommit(false);
            updateSales = con.prepareStatement(updateString);
            updateSales.setInt(1, codigo);
            updateSales.setString(2, nombre);
            updateSales.setInt(3, precio);
            updateSales.execute();
        }catch(SQLException e) {
            System.out.println("SQL exception occured - Codigo ya exite!" + e);
        }
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        String sql ="select P.nombre as nom from ORD_PRODUCTOS P,ORD_DETALLES_PEDIDO D where D.pedido_fk=? and producto_fk=P.codigo";
        con.setAutoCommit(false);
        PreparedStatement ps  = con.prepareStatement(sql);
        //asignar parámetros
        ps.setInt(1, codigoPedido);
        //usar executeQuery
        ResultSet rs=ps.executeQuery();
        //Sacar resultados del ResultSet
        while(rs.next()){
            //Llenar la lista y retornarla
            np.add(rs.getString("nom"));
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException{
        int res=-1;
        
        //Crear prepared statement
        String sql ="select SUM(P.precio*D.cantidad) as total from ORD_PRODUCTOS P,ORD_DETALLES_PEDIDO D where D.pedido_fk=? and producto_fk=P.codigo";
        con.setAutoCommit(false);
        PreparedStatement ps  = con.prepareStatement(sql);
        //asignar parámetros
        ps.setInt(1, codigoPedido);
        //usar executeQuery
        ResultSet rs=ps.executeQuery();
        //Sacar resultado del ResultSet
        if(rs.next())res=rs.getInt("total");        
        return res;
    }
    

    /**
     * Cambiar el nombre de un producto
     * @param con
     * @param codigoProducto codigo del producto cuyo nombre se cambiará
     * @param nuevoNombre el nuevo nombre a ser asignado
     */
    public static void cambiarNombreProducto(Connection con, int codigo, 
            String nuevoNombre) throws SQLException{
        
        PreparedStatement actualizarP = null;
        String updateNombre = "update ORD_PRODUCTOS set nombre = ? where codigo = ?";
        
        con.setAutoCommit(false);
        actualizarP = con.prepareStatement(updateNombre);
        actualizarP.setString(1, nuevoNombre);
        actualizarP.setInt(2, codigo);
        actualizarP.execute();
        
        
        //asignar parámetros
        //usar executeUpdate
        //verificar que se haya actualizado exactamente un registro
    }
    
}
