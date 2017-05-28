package com.johannfjs.mod3class2.database;

import com.johannfjs.mod3class2.entidades.UbicacionEntidad;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 27/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class MetodosRealm {

    public static void registrar(UbicacionEntidad ubicacionEntidad) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(ubicacionEntidad);
        realm.commitTransaction();
    }

    public static int nextId() {
        Realm realm = Realm.getDefaultInstance();
        Number number = realm.where(UbicacionEntidad.class).max("id");
        return (number != null ? number.intValue() + 1 : 1);
    }

    public static RealmResults<UbicacionEntidad> obtenerPosiciones() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UbicacionEntidad.class).findAll();
    }
}
