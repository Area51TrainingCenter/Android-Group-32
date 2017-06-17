package com.jcodee.mod2class4.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johannfjs on 10/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class SucursalResponse {
    @SerializedName("SucursalId")
    private String sucursalId;
    @SerializedName("EmpresaId")
    private String empresaId;
    @SerializedName("Codigo")
    private String codigo;
    @SerializedName("NombreComercial")
    private String nombreComercial;
    @SerializedName("Direccion")
    private String direccion;
    @SerializedName("Estado")
    private String estado;

    public String getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(String sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
