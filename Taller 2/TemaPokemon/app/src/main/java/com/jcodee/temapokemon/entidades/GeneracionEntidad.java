package com.jcodee.temapokemon.entidades;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by johannfjs on 24/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class GeneracionEntidad extends RealmObject {
    @PrimaryKey
    private long id;
    private String nombre;
    private RealmList<EspecieEntidad> especies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RealmList<EspecieEntidad> getEspecies() {
        return especies;
    }

    public void setEspecies(RealmList<EspecieEntidad> especies) {
        this.especies = especies;
    }
}
