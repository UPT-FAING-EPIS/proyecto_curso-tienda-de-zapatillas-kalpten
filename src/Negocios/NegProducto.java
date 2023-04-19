/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import Entidad.EntProducto;
import java.sql.*;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class NegProducto {

    public ResultSet MtdBuscarProducto(EntProducto Epro) {
        ResultSet rs=null;
            try{
                String consulta = "select * from productos where id='"+Epro.getId()+"' ;";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                e.printStackTrace();    
            }    
        
        
        return rs;   
    }
    
    public ResultSet MtdListar() {

        ResultSet rs=null;
            try{
                String consulta = "select * from productos";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                System.out.println(e);    
            }    
        
        
        return rs;
    }
    
    public ResultSet MtdListarBoletas() {

        ResultSet rs=null;
            try{
                String consulta = "SELECT CONCAT_WS('-',serie,numero) AS 'boleta',fecha,total FROM tbboleta";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                System.out.println(e);    
            }    
        
        
        return rs;
    }
    
    public ResultSet MtdListarEmpleados() {

        ResultSet rs=null;
            try{
                String consulta = "SELECT tbcargo.nombre,estado,tbempleado.nombre AS usuario FROM tbcargo INNER JOIN tbempleado ON tbcargo.idcargo = tbempleado.idcargo";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                System.out.println(e);    
            }    
        
        
        return rs;
    }
}
