package com.jcodee.mod2class1.modelos;

/**
 * Created by johannfjs on 8/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class NoticiaModelo {
    private int id;
    private int idCategoria;
    private String categoria;
    private String titulo;
    private String noticia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }
}
