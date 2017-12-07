package com.garcesito.restauranteapp.clases;

/**
 * Created by pepel on 8/11/2017.
 */

public class Platos {
    String Nombre;
    String Precio;
    String Descripcion;
    //String[] ing= new String[10];
    String ImagenUri,ing0,ing1,ing2,ing3,ing4,ing5,ing6,ing7,ing8,ing9;

    public Platos(String nombre, String precio, String descripcion, String imagenUri, String ing0,
                  String ing1, String ing2, String ing3, String ing4, String ing5, String ing6,
                  String ing7, String ing8, String ing9) {
        Nombre = nombre;
        Precio = precio;
        Descripcion = descripcion;
        ImagenUri = imagenUri;
        this.ing0 = ing0;
        this.ing1 = ing1;
        this.ing2 = ing2;
        this.ing3 = ing3;
        this.ing4 = ing4;
        this.ing5 = ing5;
        this.ing6 = ing6;
        this.ing7 = ing7;
        this.ing8 = ing8;
        this.ing9 = ing9;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImagenUri() {
        return ImagenUri;
    }

    public void setImagenUri(String imagenUri) {
        ImagenUri = imagenUri;
    }

    public String getIng0() {
        return ing0;
    }

    public void setIng0(String ing0) {
        this.ing0 = ing0;
    }

    public String getIng1() {
        return ing1;
    }

    public void setIng1(String ing1) {
        this.ing1 = ing1;
    }

    public String getIng2() {
        return ing2;
    }

    public void setIng2(String ing2) {
        this.ing2 = ing2;
    }

    public String getIng3() {
        return ing3;
    }

    public void setIng3(String ing3) {
        this.ing3 = ing3;
    }

    public String getIng4() {
        return ing4;
    }

    public void setIng4(String ing4) {
        this.ing4 = ing4;
    }

    public String getIng5() {
        return ing5;
    }

    public void setIng5(String ing5) {
        this.ing5 = ing5;
    }

    public String getIng6() {
        return ing6;
    }

    public void setIng6(String ing6) {
        this.ing6 = ing6;
    }

    public String getIng7() {
        return ing7;
    }

    public void setIng7(String ing7) {
        this.ing7 = ing7;
    }

    public String getIng8() {
        return ing8;
    }

    public void setIng8(String ing8) {
        this.ing8 = ing8;
    }

    public String getIng9() {
        return ing9;
    }

    public void setIng9(String ing9) {
        this.ing9 = ing9;
    }
}
