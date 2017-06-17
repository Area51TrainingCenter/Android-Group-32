package com.jcodee.temapokemon.entidades;

import io.realm.RealmObject;

/**
 * Created by johannfjs on 17/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class EspecieEntidad extends RealmObject {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
