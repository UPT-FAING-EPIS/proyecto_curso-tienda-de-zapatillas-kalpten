/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author ASUS
 */
public class EntProducto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio_normal;
    private double precio_rebajado;
    private int cantidad;
    private String imagen;
    private int id_categoria;

    public EntProducto() {
    }

    public EntProducto(int id, String nombre, String descripcion, double precio_normal, double precio_rebajado, int cantidad, String imagen, int id_categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_normal = precio_normal;
        this.precio_rebajado = precio_rebajado;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.id_categoria = id_categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_normal() {
        return precio_normal;
    }

    public void setPrecio_normal(double precio_normal) {
        this.precio_normal = precio_normal;
    }

    public double getPrecio_rebajado() {
        return precio_rebajado;
    }

    public void setPrecio_rebajado(double precio_rebajado) {
        this.precio_rebajado = precio_rebajado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }




    
    
}
