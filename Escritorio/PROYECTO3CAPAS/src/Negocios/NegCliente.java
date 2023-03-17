/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import Entidad.EntCliente;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class NegCliente {
    public ResultSet MtdBuscarDni(EntCliente ECli)
    {
        ResultSet rs=null;
            try{
                String consulta = "select * from tbcliente where dni='"+ECli.getDni()+"';";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                e.printStackTrace();    
            }    
        
        
        return rs;
    }
}
