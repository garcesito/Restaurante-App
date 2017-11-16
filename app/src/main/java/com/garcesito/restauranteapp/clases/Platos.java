package com.garcesito.restauranteapp.clases;

/**
 * Created by pepel on 8/11/2017.
 */

public class Platos {
    String Nombre;
    String Precio;
    String Descripcion;
    String[] ing= new String[10];
    String ImagenUri;



    public Platos(String nombre, String precio, String[] ing, String descripcion, String imagenUri) {
        Nombre = nombre;
        Precio = precio;
        Descripcion = descripcion;
        this.ing = ing;
        ImagenUri = imagenUri;
    }

    public String getImagenUri() {
        return ImagenUri;
    }

    public void setImagenUri(String imagenUri) {
        ImagenUri = imagenUri;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Platos() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String[] getIng() {
        return ing;
    }

    public void setIng(String[] ing) {
        this.ing = ing;
    }
}
