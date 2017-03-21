package com.jcodee.mod1class3.modelos;

import java.util.ArrayList;

/**
 * Created by johannfjs on 18/03/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Pelicula {
    private int id;
    private String nombre;
    private String estado;
    private String critica;
    private boolean terminos;
    /*private ArrayList<Pelicula> usuarios;

    public ArrayList<Pelicula> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Pelicula> usuarios) {
        this.usuarios = usuarios;
    }*/

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

    public boolean isTerminos() {
        return terminos;
    }

    public void setTerminos(boolean terminos) {
        this.terminos = terminos;
    }
}
