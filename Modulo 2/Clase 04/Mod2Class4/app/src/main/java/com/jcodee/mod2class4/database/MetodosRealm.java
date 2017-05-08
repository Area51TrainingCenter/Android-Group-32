package com.jcodee.mod2class4.database;

import com.jcodee.mod2class4.entidad.UsuarioEntidad;

import io.realm.Realm;

/**
 * Created by johannfjs on 6/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class MetodosRealm {
    public static int getNextId() {
        Realm realm = Realm.getDefaultInstance();
        Number result = realm.where(UsuarioEntidad.class).max("id");
        return (result != null ? result.intValue() : 0) + 1;
    }

    public static UsuarioEntidad validarUsuario(String usuario, String contrasena) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UsuarioEntidad.class)
                .equalTo("usuario", usuario)
                .equalTo("contrasenia", contrasena)
                .findFirst();
    }

    public static void insertarUsuario(UsuarioEntidad usuarioEntidad) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(usuarioEntidad);
        realm.commitTransaction();
    }
}
