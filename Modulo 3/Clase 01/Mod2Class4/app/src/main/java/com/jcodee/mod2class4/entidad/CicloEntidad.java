package com.jcodee.mod2class4.entidad;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by johannfjs on 13/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class CicloEntidad extends RealmObject {
    @PrimaryKey
    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
