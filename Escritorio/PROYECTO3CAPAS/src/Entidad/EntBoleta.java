/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author ASUS
 */
public class EntBoleta {
    private String serie;
    private String numero;
    private String fecha;
    private String dnicli;
    private String dniemp;
    private String estado;
    private double igv;
    private double TOTAL;

    public EntBoleta(String serie, String numero, String fecha, String dnicli, String dniemp, String estado, double igv, double TOTAL) {
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.dnicli = dnicli;
        this.dniemp = dniemp;
        this.estado = estado;
        this.igv = igv;
        this.TOTAL = TOTAL;
    }

    public EntBoleta() {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDnicli() {
        return dnicli;
    }

    public void setDnicli(String dnicli) {
        this.dnicli = dnicli;
    }

    public String getDniemp() {
        return dniemp;
    }

    public void setDniemp(String dniemp) {
        this.dniemp = dniemp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }
    
    
}
