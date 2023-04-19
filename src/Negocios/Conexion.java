/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class Conexion {
    public static Connection getConexion(){
        
        Connection cn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/card","root","admin");
            //cn = DriverManager.getConnection("jdbc:mysql://mysql-112045-0.cloudclusters.net:10019/card", "admin", "pNL8AwZL");
            System.out.println("Conexion satisfactoria");
            
        }
        catch (Exception e){
                System.out.print("Error de Conexion" + e);
        }    
        return cn;
    }
}
