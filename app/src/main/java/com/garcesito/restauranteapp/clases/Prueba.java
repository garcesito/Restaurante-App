package com.garcesito.restauranteapp.clases;

/**
 * Created by pepel on 13/11/2017.
 */

public class Prueba {
    String Nombre;
    int Precio;

    public Prueba() {
    }

    public Prueba(String nombre, int precio) {
        Nombre = nombre;
        Precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
}
