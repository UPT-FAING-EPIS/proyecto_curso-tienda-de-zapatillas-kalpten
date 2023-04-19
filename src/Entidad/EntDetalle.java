/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author ASUS
 */
public class EntDetalle {
    private String serie;
    private String numero;
    private int codproducto;
    private int uniprocompra;
    private double preunitario;
    private double subtotal;

    public EntDetalle(String serie, String numero, int codproducto, int uniprocompra, double preunitario, double subtotal) {
        this.serie = serie;
        this.numero = numero;
        this.codproducto = codproducto;
        this.uniprocompra = uniprocompra;
        this.preunitario = preunitario;
        this.subtotal = subtotal;
    }

    public EntDetalle() {
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(int codproducto) {
        this.codproducto = codproducto;
    }

    public int getUniprocompra() {
        return uniprocompra;
    }

    public void setUniprocompra(int uniprocompra) {
        this.uniprocompra = uniprocompra;
    }

    public double getPreunitario() {
        return preunitario;
    }

    public void setPreunitario(double preunitario) {
        this.preunitario = preunitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}
