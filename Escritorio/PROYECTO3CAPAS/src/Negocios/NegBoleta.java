/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import Entidad.EntBoleta;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class NegBoleta {

    public boolean MtdAgregarBoleta(EntBoleta Ebol) {
            try{
                String consulta = "insert into tbboleta(serie,numero,fecha,dnicliente,dniempleado,estado,igv,total) "
                        + "values('"+Ebol.getSerie()+"', '"+Ebol.getNumero()+"', '"+Ebol.getFecha()+"', '"
                        +Ebol.getDnicli()+"', '"+Ebol.getDniemp()+"', '"+Ebol.getEstado()+"', '"
                        +Ebol.getIgv()+"', '"+Ebol.getTOTAL()+"');";
                Statement sentencia = Conexion.getConexion().createStatement();
                sentencia.executeUpdate(consulta);
                return true;
            }
            catch(Exception e){
                e.printStackTrace();  
                return false;
            }          
    }   
}
