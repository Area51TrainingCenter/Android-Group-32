package com.jcodee.mod2class4.database;

import com.jcodee.mod2class4.entidad.CicloEntidad;
import com.jcodee.mod2class4.entidad.CursoEntidad;
import com.jcodee.mod2class4.entidad.ModuloEntidad;
import com.jcodee.mod2class4.entidad.UsuarioEntidad;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

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

    public static int getNextId(RealmModel object) {
        Realm realm = Realm.getDefaultInstance();
        Number result = realm.where(object.getClass()).max("id");
        return (result != null ? result.intValue() : 0) + 1;
    }

    public static void insertar(RealmModel realmModel) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmModel);
        realm.commitTransaction();
    }

    public static RealmResults<ModuloEntidad> obtenerModulos() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ModuloEntidad.class).findAll();
    }

    public static RealmResults<CicloEntidad> obtenerCiclo() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CicloEntidad.class).findAll();
    }

    public static RealmResults<CursoEntidad> obtenerCursos(String titulo) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CursoEntidad.class).contains("titulo", titulo).findAll();
    }
}
