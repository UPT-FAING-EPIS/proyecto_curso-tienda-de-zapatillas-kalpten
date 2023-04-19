/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import Entidad.EntDetalle;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class NegDetalle {

    public boolean MtdAgregarDetalle(EntDetalle Edet) {
        try{
                String consulta = "insert into tbdetalleboleta(serie,numero,codproducto,unidadcompra,preciounitario,subtotal) "
                        + "values('"+Edet.getSerie()+"', '"+Edet.getNumero()+"', '"+Edet.getCodproducto()+"', '"
                        +Edet.getUniprocompra()+"', '"+Edet.getPreunitario()+"', '"+Edet.getSubtotal()+"');";
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
