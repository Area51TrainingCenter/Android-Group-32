package com.jcodee.mod2class4.modelos;

import com.jcodee.mod2class4.rest.response.EmpresaResponse;

import java.util.ArrayList;

/**
 * Created by johannfjs on 10/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class PersonaModelo {
    private String nombre, apellido, correo, direccion;
    //private ArrayList<AmigoModelo> amigos;
    //private EmpresaResponse empresa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
