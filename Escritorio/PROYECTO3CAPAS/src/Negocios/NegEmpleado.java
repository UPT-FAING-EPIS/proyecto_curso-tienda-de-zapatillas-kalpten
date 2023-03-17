/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

/**
 *
 * @author ASUS
 */
import Entidad.*;
import java.sql.*;
public class NegEmpleado {
    //metodo registrar empleado 
    //metodo buscar empleado
    //metodo eliminar empleado
    //metodo listar empleado
    //metodo editar empleado
    //metodo de validar usuario(login)
    
    public ResultSet MetdValidarUsuario(EntEmpleado EEmp)
    {
        ResultSet rs=null;
            try{
                String consulta = "select * from tbempleado where dni='"+EEmp.getDni()+
                        "' and clave= '"+EEmp.getClave()+"' and idcargo= '"
                        +EEmp.getIdcargo()+"' and estado = 'ACTIVO';";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                e.printStackTrace();    
            }    
        
        
        return rs;
    }

    public ResultSet MtdBuscarDni(EntEmpleado Eemp) {
        ResultSet rs=null;
            try{
                String consulta = "select * from tbempleado where dni='"+Eemp.getDni()+"';";
                Statement sentencia = Conexion.getConexion().createStatement();
                rs = sentencia.executeQuery(consulta);
            }
            catch(Exception e){
                e.printStackTrace();    
            }    
        
        
        return rs;    
    }
}
