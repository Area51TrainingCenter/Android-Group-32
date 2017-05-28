package com.johannfjs.mod3class2_1.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johannfjs on 27/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class PostResponse {
    private int userId;
    private int id;
    @SerializedName("title")
    private String titulo;
    @SerializedName("body")
    private String cuerpo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
