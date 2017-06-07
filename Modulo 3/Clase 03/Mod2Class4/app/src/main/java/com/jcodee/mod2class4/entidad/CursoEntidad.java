package com.jcodee.mod2class4.entidad;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by johannfjs on 13/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class CursoEntidad extends RealmObject {
    @PrimaryKey
    private int id;
    private String titulo;

    private ModuloEntidad modulo;
    private CicloEntidad ciclo;

    private int idModulo;
    private int idCiclo;

    private String descripcion;

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public ModuloEntidad getModulo() {
        return modulo;
    }

    public void setModulo(ModuloEntidad modulo) {
        this.modulo = modulo;
    }

    public CicloEntidad getCiclo() {
        return ciclo;
    }

    public void setCiclo(CicloEntidad ciclo) {
        this.ciclo = ciclo;
    }
}
